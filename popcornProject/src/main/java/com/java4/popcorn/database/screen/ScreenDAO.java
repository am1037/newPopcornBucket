package com.java4.popcorn.database.screen;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScreenDAO {
    final
    SqlSessionTemplate my;

    public ScreenDAO(SqlSessionTemplate my) {
        this.my = my;
    }

    public List<String> selectAllTitle() {
        return my.selectList("ScreenDAO.selectAllTitle");
    }

    public List<ScreenVO> selectByMovieId(String movie_id) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("movie_id", movie_id);
            return my.selectList("ScreenDAO.selectByMovieId", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ScreenVO> selectByTheater(String theater_id) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("theater_id", theater_id);
            return my.selectList("ScreenDAO.selectByTheater", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ScreenVO> selectByDate(String date) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("date", date);
            return my.selectList("ScreenDAO.selectByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ScreenVO> selectAllTitleByDate(String date){
        try {
            Map<String, String> map = new HashMap<>();
            map.put("date", date);
            return my.selectList("ScreenDAO.selectAllTitleByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    @Deprecated
//    public List<ScreenVO> selectByTheater(String theater_id){
//        try {
//            Map<String, String> map = new HashMap<>();
//            map.put("theater_id", theater_id);
//            return my.selectList("ScreenDAO.selectByTheater", map);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Deprecated
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

    @Deprecated
    public List<ScreenVO> selectByTheaterAndDateAndMovieId(String theater, String day, String movie) {
        Map<String, String> map = new HashMap<>();
        map.put("theater_id", theater);
        map.put("date", day);
        map.put("movie_id", movie);
        return my.selectList("ScreenDAO.selectByTheaterAndDateAndMovieId", map);
    }


//    @Deprecated
//    public String insertJson(File file){
//        try {
//            System.out.println(file.toString());
//            ObjectMapper om = new ObjectMapper();
//            Schedule schedule = om.readValue(file, Schedule.class);
//            StringBuilder sb = new StringBuilder();
//            int count = 0;
//            for(ScreenVO vo : schedule.getMovieScreenList()){
//                try {
//                    my.insert("ScreenDAO.insertOne", vo);
//                    count++;
//                }catch (DuplicateKeyException e){
//                    //System.out.println("already exist");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            return sb.append(count).append("/").append(schedule.getMovieScreenList().size()).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error while reading file";
//        }
//    }

    @Deprecated
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

//    @Deprecated
//    public Map<String, String> insertExistingFiles(){
//        String path = "schedules/";
//        File dir = new File(path);
//        File[] files = dir.listFiles();
//        if(files == null){
//            System.out.println("no files");
//            return null;
//        }
//        Map<String, String> map = new HashMap<>();
//        for (File file : files) {
//            if (file.isFile()) {
//                map.put(file.getName(), insertJson(file));
//            }
//        }
//        return map;
//    }

    /*
    이 밑에 있는 것들은 이제 Crawler 프로젝트의 8181 http 리퀘스트로 대체합니다!
     */

//    @Deprecated
//    public int insertFromUntilByTheater(String theater, String dateFrom, String dateUntil){
//        int i1 = Integer.parseInt(dateFrom);
//        int i2 = Integer.parseInt(dateUntil);
//        int count = 0;
//        for (int i=i1; i<=i2; i++){
//            String date = String.valueOf(i);
//            System.out.println("theater: "+theater + ", date: "+date);
//            Schedule schedule;
//            try {
//                schedule = new Schedule().fromJson(new File("schedules/"+theater+"_"+date+".json"));
//                schedule.getMovieScreenList().forEach(this::insert);
//                System.out.println("theater: "+theater + ", date: "+date+" inserted");
//                count++;
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return count;
//    }


    @Deprecated
    public void truncateTable(){
        try {
            my.update("ScreenDAO.truncateScreen");
        }catch (Exception e){
            e.printStackTrace();
        }
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
}
