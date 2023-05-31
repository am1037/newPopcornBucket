package com.java4.popcorn.controllers.alarm;

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

    Map<String, String> movieOnScreen;
    Map<String, String> movieIdToTitleMap;
    Map<String, String> theaterIdToNameMap;
    Set<String> regionSet;
    public SharedPropertiesStore(ScreenDAO screenDAO, TheaterDAO theaterDAO) {
        this.screenDAO = screenDAO;
        this.theaterDAO = theaterDAO;
        setPropertiesFromDB();
        setMovieIdToTitleMap();
    }
    public void setPropertiesFromDB(){
        List<TheaterVO> theaterVOs = theaterDAO.selectAllTheaterCode();
        List<ScreenVO> screenVOs = new ArrayList<>();
        for(TheaterVO theaterVO : theaterVOs){
            screenVOs.addAll(screenDAO.selectByTheater(theaterVO.getTheater_id()));
        }
        movieOnScreen = new HashMap<>();
        for (ScreenVO screenVO : screenVOs) {
            movieOnScreen.put(screenVO.getMovie_docid(), screenVO.getTitle());
        }
        theaterIdToNameMap = new HashMap<>();
        for (TheaterVO theaterVO : theaterVOs) {
            theaterIdToNameMap.put(theaterVO.getTheater_id(), theaterVO.getTheater_name());
        }
//        regionSet = theaterDAO.selectAllRegion();
        regionSet = new HashSet<>();
        for (TheaterVO theaterVO : theaterVOs) {
            regionSet.add(theaterVO.getTheater_region());
        }
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setMovieIdToTitleMap() {
        Map<String, String> map = new HashMap<>();

        for (String movieId : movieOnScreen.keySet()) {
            map.put(movieId, movieOnScreen.get(movieId));
        }
        map.remove(null);
        movieIdToTitleMap = map;
    }
}
