package com.java4.popcorn.database.movie.mapping;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class MovieMappingVO {
    String company;
    String detail_url;
    String my_id;
    String docid;
    String my_title;
    String kmdb_url;
    String posters;

    List<String> posterList;
    public void setPosters(String posters) {
        posterList = Arrays.asList(posters.split("\\|"));
        this.posters = posters;
    }
}

