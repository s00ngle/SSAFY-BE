package com.ssafy.ws.step2.dao;

import com.ssafy.ws.step2.dto.Movie;
import com.ssafy.ws.step2.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

	private static final String INSERT_MOVIE_SQL = "INSERT INTO movies (Title, Director, Genre, RunningTime) VALUES (?, ?, ?, ?)";
	private static final String SELECT_ALL_MOVIES_SQL = "SELECT * FROM movies";
	private static final String SELECT_MOVIE_BY_ID_SQL = "SELECT * FROM movies WHERE Id = ?";
	private static final String UPDATE_MOVIE_SQL = "UPDATE movies SET Title = ?, director = ?, Genre = ?, RunningTime = ? WHERE Id = ?";
	private static final String DELETE_MOVIE_SQL = "DELETE FROM movies WHERE Id = ?";
    private static final String COUNT_MOVIES_SQL = "SELECT COUNT(*) FROM movies";

	private static final String CREATE_TABLE_SQL = 
		    "CREATE TABLE `movies` ( " +
		    "`ID` int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
		    "`Title` varchar(40) CHARACTER SET utf8mb4 NOT NULL, " +
		    "`Director` varchar(40) CHARACTER SET utf8mb4 NOT NULL, " +
		    "`Genre` varchar(40) CHARACTER SET utf8mb4 NOT NULL, " +
		    "`RunningTime` int(3) NOT NULL " +
		    ") ENGINE=InnoDB;";

	private static MovieDaoImpl instance = new MovieDaoImpl();
	
	private MovieDaoImpl() {
		
	}
	
	public static MovieDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public void insertMovie(Movie movie) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL);
			
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getDirector());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setInt(4, movie.getRunningTime());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public List<Movie> selectAllMovies() throws Exception {
		List<Movie> movies = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES_SQL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Movie movie = new Movie();
				movie.setId(resultSet.getInt("id"));
				movie.setTitle(resultSet.getString("title"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(resultSet.getString("genre"));
				movie.setRunningTime(Integer.parseInt(resultSet.getString("runningTime")));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return movies;
	}

	@Override
	public Movie getMovieById(int id) throws Exception {
		Movie movie = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID_SQL);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				movie = new Movie();
				movie.setId(resultSet.getInt("id"));
				movie.setTitle(resultSet.getString("title"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(resultSet.getString("genre"));
				movie.setRunningTime(Integer.parseInt(resultSet.getString("runningTime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return movie;
	}

	@Override
	public void updateMovie(Movie movie) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_MOVIE_SQL);

			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getDirector());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setInt(4, movie.getRunningTime());
			preparedStatement.setInt(5, movie.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public void deleteMovie(int id) throws Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_MOVIE_SQL);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public void createTableIfNotExists() throws Exception {
		Connection connection = null;
        Statement statement = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            statement.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtil.close(statement, connection);
        }
	}

	@Override
	public int movieCount() throws Exception {
	    int count = 0;

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBUtil.getConnection();
	        preparedStatement = connection.prepareStatement(COUNT_MOVIES_SQL);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            count = resultSet.getInt(1);  // 첫 번째 컬럼에서 총 레코드 개수를 가져옴
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        DBUtil.close(resultSet, preparedStatement, connection);
	    }

	    return count;
	}
}
