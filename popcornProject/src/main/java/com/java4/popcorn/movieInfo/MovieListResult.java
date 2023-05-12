package com.java4.popcorn.movieInfo;



import java.util.List;

public class MovieListResult {
    String totCnt;
    String source;
    List<MovieVO> movieList;
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<MovieVO> getMovieList() {
		return movieList;
	}
	public void setMovieList(List<MovieVO> movieList) {
		this.movieList = movieList;
	}
	@Override
	public String toString() {
		return "MovieListResult [totCnt=" + totCnt + ", source=" + source + ", movieList=" + movieList
				+ ", getTotCnt()=" + getTotCnt() + ", getSource()=" + getSource() + ", getMovieList()=" + getMovieList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
