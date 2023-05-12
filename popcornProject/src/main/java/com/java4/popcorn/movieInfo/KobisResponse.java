package com.java4.popcorn.movieInfo;


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
