package Question2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Question1.Movie;

public class MovieManager {

	DataSource ds;
	
	public MovieManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java.comp/env/jdbc/JDBCDataSourceDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	
	
	//void createMovie
	public void createMovie(Movie newMovie){
		String createMovieSql = "INSERT INTO MOVIE (id,title, posterImage,releaseDate) VALUES (?, ?, ?, ?);";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createMovieSql);
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, newMovie.getTitle());
			statement.setString(3, newMovie.getPosterImage());
			Date date = new Date(newMovie.getReleaseDate().getTime());
			statement.setDate(4, date);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	//List<Movie> readAllMovies()
	public List<Movie> readAllMovies(){
		String readAllMoviesSql = "SELECT * FROM MOVIE;";
		List<Movie> movies = new ArrayList<Movie>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllMoviesSql);
			results = statement.executeQuery();
			while (results.next()) {
				Movie movie = new Movie();
				movie.setId(results.getString("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				Date date = new Date(results.getDate("releaseDate").getTime());
				movie.setReleaseDate(date);
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return movies;
	}
	
	
	
	//Movie readMovie
	public Movie readMovie(String movieId){
		String readMovie = "SELECT * FROM MOVIE WHERE id=?;";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readMovie);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			if (results.next()) {
				Movie movie = new Movie();
				movie.setId(results.getString("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				Date date = new Date(results.getDate("releaseDate").getTime());
				movie.setReleaseDate(date);
				return movie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	} 
	
	
	
	//void updateMovie
	public void updateMovie(String movieId, Movie movie){
		String updateMovieSql = "UPDATE MOVIE SET TITLE=?,POSTERIMAGE=?,RELEASEDATE=? WHERE id=?;";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateMovieSql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			Date date = new Date(movie.getReleaseDate().getTime());
			statement.setDate(3, date);
			statement.setString(4, movieId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//void  delete movie
	public void deleteMovie(String movieId){
		String deleteUserSql = "DELETE FROM MOVIE WHERE id=?";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, movieId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
}
