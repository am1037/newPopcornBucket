package com.java4.popcorn.database.screen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.api.kmdb.KmdbAPI;
import com.java4.popcorn.api.kmdb.KmdbMovieSimpleInfoResponseVO;
import com.java4.popcorn.database.cgv.CGV;
import com.java4.popcorn.database.theater.TheaterVO;
import com.java4.popcorn.database.cgv.Schedule;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class ScreenDAO {
    final
    SqlSessionTemplate my;

    List<TheaterVO> theaterCodes;

    public ScreenDAO(SqlSessionTemplate my, KmdbAPI kmdbAPI) {
        this.my = my;
        this.kmdbAPI = kmdbAPI;
    }

    public List<ScreenVO> selectByTheaterAndDate(String theater, String date){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater);
            map.put("date", date);
            return my.selectList("ScreenDAO.selectByTheaterAndDate", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ScreenVO> selectByTheater(String theater_id){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater_id);
            return my.selectList("ScreenDAO.selectByTheater", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String insertJson(File file){
        try {
            System.out.println(file.toString());
            ObjectMapper om = new ObjectMapper();
            Schedule schedule = om.readValue(file, Schedule.class);
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for(ScreenVO vo : schedule.getMovieScreenList()){
                try {
                    my.insert("ScreenDAO.insertOne", vo);
                    count++;
                }catch (DuplicateKeyException e){
                    //System.out.println("already exist");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return sb.append(count).append("/").append(schedule.getMovieScreenList().size()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "error while reading file";
        }
    }
    public int insert(ScreenVO ms){
        try {
            //System.out.println(ms);
            my.insert("ScreenDAO.insertOne", ms);
        } catch (DuplicateKeyException e) {
            System.out.println("already exist");
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return -1;
    }


    public Map<String, String> insertExistingFiles(){
        String path = "schedules/";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null){
            System.out.println("no files");
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (File file : files) {
            if (file.isFile()) {
                map.put(file.getName(), insertJson(file));
            }
        }
        return map;
    }

    public int updateMovieIdByTitle(String title, String movie_id){
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("movie_id", movie_id);
        try {
            return my.update("ScreenDAO.updateMovieIdByTitle", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    /*
    나중에 파일 출력 부분도 분리하기!
     */
    final
    KmdbAPI kmdbAPI;
    @Deprecated
    public int updateMovieId(){
        Map<String, String> titleToDOCIDMap = new HashMap<>();
        Map<String, KmdbMovieSimpleInfoResponseVO> errorMap = new HashMap<>();
        Map<String, KmdbMovieSimpleInfoResponseVO> warnMap = new HashMap<>();
        Map<String, Integer> titleAndRuntimeMap = getOnScreenTitleAndRuntimeList();
        for (String title : titleAndRuntimeMap.keySet()) {
            KmdbMovieSimpleInfoResponseVO kmdbVo = kmdbAPI.getMovieInfoByTitle(title);
            //System.out.println(title+" : "+kmdbVo);
            if (kmdbVo.getTotalCount() == 1) {
                //System.out.println(title+" : "+kmdbVo.getData().get(0).getResult().get(0).getDOCID());
                titleToDOCIDMap.put(title, kmdbVo.getData().get(0).getResult().get(0).getDOCID());
            } else {
                try {
                    Integer runtime = titleAndRuntimeMap.get(title);
                    kmdbVo.getMovies(0).forEach(m -> {
                        Integer kmdbRuntime = Integer.parseInt(m.getRuntime().trim());
                        if (kmdbRuntime.equals(runtime)) {
                            titleToDOCIDMap.put(title, m.getDOCID());
                            warnMap.put(title, kmdbVo);
                        }
                    });
                } catch (NumberFormatException e){
                    System.out.println("NumberFormatException : "+title);
                    System.out.println("e.getMessage() : "+e.getMessage());
                } catch (NullPointerException e){
                    System.out.println("NullPointerException : "+title);
                    System.out.println("e.getMessage() : "+e.getMessage());
                }
            }
            if (!titleToDOCIDMap.containsKey(title)) {
                errorMap.put(title, kmdbVo);
            }
        }
        int count = 0;
        for (String key : titleToDOCIDMap.keySet()) {
            try {
                count += updateMovieIdByTitle(key, titleToDOCIDMap.get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //for errorMap
        System.out.println("Error List");
        for (String key : errorMap.keySet()) {
            System.out.println(key);
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File("error/" + key + ".json"), errorMap.get(key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Error List End");

        //for warnMap
        System.out.println("Warn List");
        for (String key : warnMap.keySet()) {
            System.out.println(key);
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File("error/warn_" + key + ".json"), warnMap.get(key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Warn List End");

        return count;
    }

    @Deprecated
    public int insertFromUntilByTheater(String theater, String dateFrom, String dateUntil){
        int i1 = Integer.parseInt(dateFrom);
        int i2 = Integer.parseInt(dateUntil);
        int count = 0;
        for (int i=i1; i<=i2; i++){
            String date = String.valueOf(i);
            System.out.println("theater: "+theater + ", date: "+date);
            Schedule schedule;
            try {
                schedule = new Schedule().fromJson(new File("schedules/"+theater+"_"+date+".json"));
                schedule.getMovieScreenList().forEach(this::insert);
                System.out.println("theater: "+theater + ", date: "+date+" inserted");
                count++;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public List<TheaterVO> selectAllTheaterCode(){
        try {
            return my.selectList("TheaterDAO.selectAllTheater");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void truncateTable(){
        try {
            my.update("ScreenDAO.truncateScreen");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getOnScreenTitleAndRuntimeList(){
        try {
            List<ScreenVO> msl = my.selectList("ScreenDAO.selectAll");
            Map<String, Integer> map = new HashMap<>();
            for (ScreenVO ms : msl){
                map.put(ms.getTitle(), ms.getRuntime());
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ScreenVO> selectAll(){
        try {
            //System.out.println(msl);
            return my.selectList("ScreenDAO.selectAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Deprecated
    public List<ScreenVO> select0513(String str1, String str2){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("str1", str1);
            map.put("str2", str2);
            return my.selectList("ScreenDAO.select0513", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String test2(String tfs) {
        String[] tfL = tfs.split(",");
        try {
            int i = Integer.parseInt(tfL[0]);
        }catch (Exception e){
            return "땡!";
        }
        List<ScreenVO> msL = new ArrayList<>();
        for(String t:tfL){
            msL.addAll(selectByTheater(t));
            System.out.println("msL: "+msL);
        }
        Map<String, Integer> map = CGV.count(msL);
        List<String> strings = new ArrayList<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> strings.add(e.getKey()));
        for(String s: strings){
            System.out.println(s+" "+map.get(s));
        }

        //
        List<String> lightList = new ArrayList<>();
        String str;
        while (strings.size()>0) {
            str = strings.remove(0);
            lightList.add(str);
        }

        System.out.println("lightList: "+lightList);

        StringBuilder sb = new StringBuilder();
        for(String s: tfL){
            TheaterVO vo = my.selectOne("TheaterDAO.selectOneTheater", s);
            String name = vo.getTheater_name();
            sb.append(s).append(" : ").append(name).append("\n");
        }
        sb.append("의 상영횟수 입니다.\n");
        for(String s: lightList){
            sb.append(s).append(" : ").append(map.get(s)).append("\n");
        }

        return sb.toString();
    }

    public List<ScreenVO> selectByTheaterAndDateAndMovieId(String theater, String day, String movie) {
        Map<String, String> map = new HashMap<>();
        map.put("theater_id", theater);
        map.put("date", day);
        map.put("movie_id", movie);
        return my.selectList("ScreenDAO.selectByTheaterAndDateAndMovieId", map);
    }

    public List<String> selectAllTitle() {
        return my.selectList("ScreenDAO.selectAllTitle");
    }
}
