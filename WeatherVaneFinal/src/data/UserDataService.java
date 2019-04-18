package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

//SLF4L logger imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.User;
import util.DataFactory;
import util.DatabaseException;
import util.LoggingIntercepter;
/**
 * @Author Jerran Fredricks/Joe Lean
 * 10/21/2018
 **/
@Stateless
@Local(UserDataAccessInterface.class)
@LocalBean
@Interceptors(LoggingIntercepter.class)
public class UserDataService implements UserDataAccessInterface<User> {

	DataFactory dataFactory = new DataFactory();

	Logger logger = LoggerFactory.getLogger(UserDataService.class);
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/weathervane";
	String username = "root";
	String password = "root";
	

	@Override
	public List<User> findAll() {
		logger.info("findAll() called");
		
		List<User> users = new ArrayList<User>();
		try {
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query and loop over result set
			String sql1 = "SELECT * FROM USER";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				User user = new User(rs1.getString("USERNAME"), rs1.getString("EMAIL"), rs1.getString("PASSWORD"),
						rs1.getString("FIRSTNAME"), rs1.getString("LASTNAME"), rs1.getInt("PERMISSION"));
				users.add(user);
			}

			// Cleanup
			rs1.close();
			stmt1.close();
		} catch (SQLException e) {
			logger.error("An SQLException or DatabaseException has Occured", new Exception(e));
			e.printStackTrace();
			throw new DatabaseException(e);
		} finally {
			// Cleanup Database
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("An SQLException or DatabaseException has Occured", new Exception(e));
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
		}

		logger.info("leaving findAll()");
		// Return list of Users
		return users;
	}

	@Override
	public User findById(int id) {
				logger.info("findById() called");
				User user = new User();
		try {
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query and loop over result set
			String sql1 = String.format("SELECT * FROM USER WHERE USER_ID='%S'",id);
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			user = new User(rs1.getString("USERNAME"), rs1.getString("EMAIL"), rs1.getString("PASSWORD"),
					rs1.getString("FIRSTNAME"), rs1.getString("LASTNAME"), rs1.getInt("PERMISSION"));
			// Cleanup
			rs1.close();
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		} finally {
			// Cleanup Database
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
		}
		return user;
	}
	/**A more general search for any user. This time, it searches by Email.
	 * @param
	 * @return user
	 */
	@Override
	public User findBy(User user) 
	{
		User user2 = new User();
		logger.info("findBy() called");	
		try {
			// Connect to the Database
			
			conn = DriverManager.getConnection(url, username, password);
			// Execute SQL Query and loop over result set
			String sql1 = String.format("SELECT * FROM USER WHERE EMAIL= '%S'", user.getEmail());
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next())
			{
			user2 = new User(rs1.getString("USERNAME"), rs1.getString("EMAIL"), rs1.getString("PASSWORD"),
					rs1.getString("FIRSTNAME"), rs1.getString("LASTNAME"), rs1.getInt("PERMISSION"));
			}
			
			rs1.close();
			stmt1.close();
		} catch (SQLException e) {
			logger.error("An SQLException or DatabaseException has Occured", new Exception(e));
			e.printStackTrace();
			throw new DatabaseException(e);
		} finally {
			// Cleanup Database
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("An SQLException or DatabaseException has Occured", new Exception(e));
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
		}
		logger.info("leaving findBy()");
		return user2;
	}

	
	@Override
	public boolean create(User user) {
				logger.info("create() called");
		// Insert User
				try {
					// Connect to the Database
					conn = DriverManager.getConnection(url, username, password);

					// Insert an User
					String sql1 = String.format("INSERT INTO USER(USERNAME, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PERMISSION) VALUES('%s', '%s', '%s','%s', '%s', %d)",
							user.getUserName(),user.getEmail(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getPermissions());
					Statement stmt1 = conn.createStatement();
					stmt1.executeUpdate(sql1);
					stmt1.close();
				} catch (SQLException e) {
					logger.error("An SQLException or DatabaseException has Occured", new Exception(e));
					e.printStackTrace();
					throw new DatabaseException(e);
				} finally {
					// Cleanup Database
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("An SQLException or DatabaseException has Occured", new Exception(e));
							e.printStackTrace();
							throw new DatabaseException(e);
						}
					}
				}

				// Return OK
				logger.info("leaving create()");
				return true;
	}
	//to be implemented later
	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	//to be implemented later
	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
