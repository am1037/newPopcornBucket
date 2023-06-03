package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.database.movie.mapping.MovieMappingDAO;
import com.java4.popcorn.database.movie.mapping.MovieMappingVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.screen.ScreenVO;
import com.java4.popcorn.database.theater.TheaterDAO;
import com.java4.popcorn.database.theater.TheaterVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Data
public class SharedPropertiesStore {

    final ScreenDAO screenDAO;
    final TheaterDAO theaterDAO;
    final MovieMappingDAO movieMappingDAO;

    Map<String, String> theaterIdToNameMap;
    Set<String> regionSet;

    public SharedPropertiesStore(ScreenDAO screenDAO, TheaterDAO theaterDAO, MovieMappingDAO movieMappingDAO) {
        this.screenDAO = screenDAO;
        this.theaterDAO = theaterDAO;
        this.movieMappingDAO = movieMappingDAO;

        setProperties();
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String dateString;
    String dateStringPlus4day;
    List<String> dateStrings;

    public String setProperties(){
        setDate();
        setTheaterVOs();
        setRegion();
        setMovieIdToTitleMap();
        setMovieOnScreen(); // 미리 날짜 필요
        setMovieOnScreenMap();
        return new Date().toString();
    }
    public void setDate(){
        String dateString = sdf.format(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        String dateStringPlus4day = sdf.format(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 4);
        dateStrings = betweenTwoDate(dateString, dateStringPlus4day);
    }
    public void setRegion(){
        regionSet = new HashSet<>();
        for (TheaterVO theaterVO : theaterVOs) {
            regionSet.add(theaterVO.getTheater_region());
        }
    }

    List<TheaterVO> theaterVOs;
    public void setTheaterVOs(){
        theaterVOs = theaterDAO.selectAllTheaterCode();
        theaterIdToNameMap = new HashMap<>();
        for (TheaterVO theaterVO : theaterVOs) {
            theaterIdToNameMap.put(theaterVO.getTheater_id(), theaterVO.getTheater_name());
        }
    }

    Set<String> movieOnScreen;
    public void setMovieOnScreen(){
        movieOnScreen = new HashSet<>();
        for (String dateString : dateStrings) {
            movieOnScreen.addAll(screenDAO.selectDistinctMovieIdByDate(dateString));
        }
    }

    //movieOnScreenMap은 상영관이 적은 순서대로 정렬되어 있습니다.
    //key가 되는 String은 movieId이고, value는 해당 movieId를 가진 영화가 상영되는 상영관의 목록입니다.

    Map<String, List<ScreenVO>> movieOnScreenMap;
    List<MovieMappingVO> movieOnScreenMapList; // rank by lowest
    public void setMovieOnScreenMap(){
        movieOnScreenMap = new HashMap<>();
        for (String str : movieOnScreen) {
            movieOnScreenMap.putIfAbsent(str, new ArrayList<>());
            for(String date : dateStrings) {
                movieOnScreenMap.get(str).addAll(screenDAO.selectByMovieIdAndDate(str, date));
            }
        }

        // Convert the entries to a list
        List<Map.Entry<String, List<ScreenVO>>> entryList = new ArrayList<>(movieOnScreenMap.entrySet());

        // Sort the list by the size of the List values
        entryList.sort(Comparator.comparingInt(o -> o.getValue().size()));

        // Create a new LinkedHashMap and add the sorted entries
        Map<String, List<ScreenVO>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<ScreenVO>> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        movieOnScreenMap = sortedMap;
        movieOnScreenMapList = new ArrayList<>();
        sortedMap.forEach((k, v) -> {
                System.out.println(k + " : " + movieIdToTitleMap.get(k) + " : " + v.size());
                movieOnScreenMapList.add(movieMappingDAO.selectOneByMovieId(k).get(0));
        });
    }

    Map<String, String> movieIdToTitleMap;
    public void setMovieIdToTitleMap() {
        Map<String, String> map = new HashMap<>();

        List<MovieMappingVO> mmvl = movieMappingDAO.selectAll();

        for(MovieMappingVO mmv : mmvl) {
        	map.put(mmv.getDocid(), mmv.getMy_title());
        }

        map.remove("!NOT FOUND");
        map.remove(null);
        movieIdToTitleMap = map;
    }

    public List<String> betweenTwoDate(String date1, String date2){
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf.parse(date1);
            while (!sdf.format(date).equals(date2)){
                list.add(sdf.format(date));
                date = new Date(date.getTime() + (1000 * 60 * 60 * 24));
            }
            list.add(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
