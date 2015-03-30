package Question3;

import java.sql.Connection;
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

import Question1.Cast;

public class CastManager {
	
	DataSource ds;
	
	public CastManager() {
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
	
	
	
	
	//void createCast
	public void createCast(Cast newCast){
		String createCastSql = "INSERT INTO CAST (id, characterName, actorId,movieId) VALUES (?, ?, ?, ?);";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCastSql);
			// this need the UUID.jar 
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, newCast.getCharacterName());
			statement.setString(3, newCast.getActorId());
			statement.setString(4, newCast.getMovieId());
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
	
	
	
	
	
	
	//List<Cast> readAllCast()
	public List<Cast> readAllCast(){
		String readAllCastsSql = "SELECT * FROM CAST;";
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsSql);
			results = statement.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getString("actorId"));
				cast.setMovieId(results.getString("movieId"));
				casts.add(cast);
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
		return casts;
	}
	
	
	
	
	
	
	//List<Cast> readAllCastForActor
	public List<Cast> readAllCastForActor(String actorId){
		String readAllCastForActorSql = "SELECT * FROM CAST WHERE ACTORID = ?;";
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastForActorSql);
			statement.setString(1, actorId);
			results = statement.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getString("actorId"));
				cast.setMovieId(results.getString("movieId"));
				casts.add(cast);
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
		return casts;
	}
	
	
	
	
	
	
	//List<Cast> readAllCastForMovie
	public List<Cast> readAllCastForMovie(String movieId){
		String readAllCastForMovieSql = "SELECT * FROM CAST WHERE MOVIEID = ?;";
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastForMovieSql);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			while (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getString("actorId"));
				cast.setMovieId(results.getString("movieId"));
				casts.add(cast);
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
		return casts;
	}
	
	
	
	
	
	//Cast readCastForld
	public Cast readCastForId(String castId){
		String readCastForId = "SELECT * FROM CAST WHERE id=?;";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readCastForId);
			statement.setString(1, castId);
			results = statement.executeQuery();
			if (results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getString("actorId"));
				cast.setMovieId(results.getString("movieId"));
				return cast;
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
	
	
	
	
	
	
	//void updateCast
	public void updateCast(String castId, Cast newCast){
		String updateCastSql = "UPDATE CAST SET CHARACTERNAME=?,ACTORID=?,MOVIEID=? WHERE id=?;";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCastSql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getActorId());
			statement.setString(3, newCast.getMovieId());
			statement.setString(4, castId);
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
	
	
	
	
	
	//void deleteCast
	public void deleteCast(String castId){
		String deleteUserSql = "DELETE FROM CAST WHERE id=?";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, castId);
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
