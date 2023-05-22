package com.java4.popcorn.database.movieInfo;

import com.java4.popcorn.GeneralDAO;
import com.java4.popcorn.api.Kobis.KobisMovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class KobisMovieDAO {
    @Autowired
    GeneralDAO<KobisMovieVO> dao;

    public boolean insertOne(KobisMovieVO vo) {
        dao.setVo(vo);
        return dao.insertOne();
    }

}