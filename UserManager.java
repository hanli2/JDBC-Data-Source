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

import Question1.User;

public class UserManager {

	DataSource ds;
	
	public UserManager() {
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
	

	
	//void createUser(User newUser)
	public void createUser(User newUser) {
		String createUserSql = "INSERT INTO USER (USERNAME, PASSWORD,FIRSTNAME,LASTNAME,EMAIL,DATEOFBIRTH) VALUES (?, ?, ?, ?, ?, ?);";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createUserSql);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			Date date = new Date(newUser.getDateOfBirth().getTime());
			statement.setDate(6, date);
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
		
		
		//List<User> readAllUsers()
		public User readUser(String username) {
			String readAllUsersSql = "SELECT * FROM USER;";
			List<User> users = new ArrayList<User>();
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(readAllUsersSql);
				results = statement.executeQuery();
				while (results.next()) {
					User user = new User();
					user.setUsername(results.getString("username"));
					user.setPassword(results.getString("password"));
					user.setFirstName(results.getString("firstname"));
					user.setLastName(results.getString("lastname"));
					user.setEmail(results.getString("email"));
					user.setDateOfBirth(results.getDate("dateofbirth"));
					users.add(user);
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
			return users;
		}
		
		
		
		//User readUser
		public void readUser(String username) {
			String readUserSql = "SELECT * FROM USER WHERE USERNAME=?;";
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(readUser);
				statement.setString(1, username);
				results = statement.executeQuery();
				if (results.next()) {
					User user = new User();
					user.setUsername(results.getString("username"));
					user.setPassword(results.getString("password"));
					user.setFirstName(results.getString("firstname"));
					user.setLastName(results.getString("lastname"));
					user.setEmail(results.getString("email"));
					user.setDateOfBirth(results.getDate("dateofbirth"));
					return user;
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
		
		
		
		//void updateUser
		public void updateUser(String username, User user) {
			String updateUserSql = "UPDATE USER SET PASSWORD=?,FIRSTNAME=?,LASTNAME=?,EMAIL=?,DATEOFBIRTH=? WHERE USERNAME=?;";
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(updateUserSql);
				statement.setString(1, user.getPassword());
				statement.setString(2, user.getFirstName());
				statement.setString(3, user.getLastName());
				statement.setString(4, user.getEmail());
				Date date = new Date(user.getDateOfBirth().getTime());
				statement.setDate(5, date);
				statement.setString(6, username);
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
		
		
		
		
		// void deleteUser
		public void deleteUser(String username) {
			String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?";
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(deleteUserSql);
				statement.setString(1, username);
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
}
