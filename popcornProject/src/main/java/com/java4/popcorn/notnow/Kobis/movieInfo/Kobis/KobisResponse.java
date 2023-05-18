package com.java4.popcorn.notnow.Kobis.movieInfo.Kobis;


import com.java4.popcorn.notnow.Kobis.movieInfo.MovieListResult;

public class KobisResponse {
    MovieListResult movieListResult;

	public MovieListResult getMovieListResult() {
		return movieListResult;
	}

	public void setMovieListResult(MovieListResult movieListResult) {
		this.movieListResult = movieListResult;
	}

	@Override
	public String toString() {
		return "KobisResponse [movieListResult=" + movieListResult + ", getMovieListResult()=" + getMovieListResult()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
}
