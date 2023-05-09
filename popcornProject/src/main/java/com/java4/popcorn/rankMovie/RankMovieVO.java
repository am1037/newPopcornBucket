package com.java4.popcorn.rankMovie;

import lombok.Data;

@Data
public class RankMovieVO {
    private int movieId;
    private String movieTitle;
    private Integer movieLike;
    private Integer movieHits;
    private Integer moviePopularity;
    private Integer movieReviews;
}
