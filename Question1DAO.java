package jds.ds.dao;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Question1.Actor;
import Question1.Cast;
import Question1.Comment;
import Question1.Movie;
import Question1.User;

public class Question1DAO 
{
	
	DataSource ds;
	
	public Question1DAO()
	{
		try{
		  Context ctx = new InitialContext();
		  ds = (DataSource)ctx.lookup("java.comp/env/jdbc/JDBCDataSourceDB");
		  System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();	
		}
		
	}

	
//create User
public User user(User user)
{
	return null;
}


//create Comment
public Comment comment(Comment comment)
{
	return null;
}


//create Movie
public Movie movie(Movie movie)
{
	return null;
}

//create Cast
public Cast cast(Cast cast)
{
	return null;
}


//create Actor
public Actor actor(Actor actor)
{
	return null;
}


}