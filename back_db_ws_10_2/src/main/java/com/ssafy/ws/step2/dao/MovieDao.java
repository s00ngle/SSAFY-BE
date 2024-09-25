package com.ssafy.ws.step2.dao;

import java.util.List;

import com.ssafy.ws.step2.dto.Movie;

public interface MovieDao {
	void insertMovie(Movie movie) throws Exception;
	List<Movie> selectAllMovies() throws Exception;
	Movie getMovieById(int id) throws Exception;
	void updateMovie(Movie movie) throws Exception;
	void deleteMovie(int id) throws Exception;
	void createTableIfNotExists() throws Exception;
	int movieCount() throws Exception;
}