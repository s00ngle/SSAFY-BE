package com.ssafy.sample.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.sample.dto.Movie;
import com.ssafy.sample.model.dao.MoviedaoImpl;

public class MovieServiceImpl implements MovieService {
	
	
	public MovieServiceImpl() {}

	static MovieServiceImpl instance = new MovieServiceImpl();
	
	public static MovieServiceImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Movie> selectAll() throws SQLException {
		return MoviedaoImpl.getInstance().selectAll();
	}

	@Override
	public Movie selectByCode(String code) throws SQLException {
		return MoviedaoImpl.getInstance().selectByCode(code);
	}

	@Override
	public int insert(Movie movie) throws SQLException {
		return MoviedaoImpl.getInstance().insert(movie);
	}

	@Override
	public int deleteByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Movie movie) throws SQLException {
		return MoviedaoImpl.getInstance().update(movie);
	}

}
