package com.java4.popcorn.movieInfo;

import lombok.Data;

import java.util.List;
@Data
public class MovieListResult {
    String totCnt;
    String source;
    List<MovieVO> movieList;
}
