package com.ssafy.sample.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.sample.dto.Movie;
import com.ssafy.sample.util.DBUtil;

public class MoviedaoImpl implements MovieDao {

	public MoviedaoImpl() {
	}

	static MoviedaoImpl instance = new MoviedaoImpl();

	public static MoviedaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Movie> selectAll() throws SQLException {
		List<Movie> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DBUtil.getInstance().getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from movies");

		pstmt = conn.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Movie movie = new Movie();
			movie.setCode(rs.getString("mcode"));
			movie.setTitle(rs.getString("mtitle"));
			movie.setTime(rs.getInt("mtime"));
			movie.setDirector(rs.getString("mdirector"));
			movie.setGenre(rs.getString("mgenre"));
			
			list.add(movie);
		}

		return list;
	}

	@Override
	public Movie selectByCode(String code) throws SQLException {
		Movie movie = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DBUtil.getInstance().getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from movies where mcode = ?");

		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1,  code);
		
		rs = pstmt.executeQuery();

		if (rs.next()) {
			movie = new Movie();
			movie.setCode(rs.getString("mcode"));
			movie.setTitle(rs.getString("mtitle"));
			movie.setTime(rs.getInt("mtime"));
			movie.setDirector(rs.getString("mdirector"));
			movie.setGenre(rs.getString("mgenre"));
		}

		return movie;
	}

	@Override
	public int insert(Movie movie) throws SQLException {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = DBUtil.getInstance().getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into movies \n");
		sql.append("values (?, ?, ?, ?, ?)");
		
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, movie.getCode());
		pstmt.setString(2, movie.getTitle());
		pstmt.setInt(3, movie.getTime());
		pstmt.setString(4, movie.getDirector());
		pstmt.setString(5, movie.getGenre());
		
		cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int deleteByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Movie movie) throws SQLException {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = DBUtil.getInstance().getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("update movies \n");
		sql.append("set mtitle = ?, mtime = ?, mdirector = ?, mgenre = ? \n");
		sql.append("where mcode = ?");
		
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, movie.getTitle());
		pstmt.setInt(2, movie.getTime());
		pstmt.setString(3, movie.getDirector());
		pstmt.setString(4, movie.getGenre());
		pstmt.setString(5, movie.getCode());
		
		cnt = pstmt.executeUpdate();
		
		return cnt;
	}

}
