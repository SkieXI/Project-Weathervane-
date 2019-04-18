package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import beans.WeatherData;

import util.DatabaseException;
import util.LoggingIntercepter;
/**
 * @Author Jerran Fredricks/Joe Lean
 * 10/21/2018
 **/
@Stateless
@Local(WeatherDataAccessInterface.class)
@LocalBean
@Interceptors(LoggingIntercepter.class)
public class WeatherDataService implements WeatherDataAccessInterface<WeatherData> {
	
	Logger logger = LoggerFactory.getLogger(UserDataService.class);
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/weathervane";
	String username = "root";
	String password = "root";

	@Override
	public List<WeatherData> findAll(){
				logger.info("findAll() called");

		List<WeatherData> datas = new ArrayList<WeatherData>();
		try {
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query and loop over result set
			String sql1 = "SELECT * FROM WEATHERDATA";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				WeatherData data = new WeatherData(rs1.getDouble("TEMPF"),rs1.getDouble("TEMPC"),rs1.getDouble("HUMIDITY"),rs1.getDouble("PRESSURE"));
				datas.add(data);
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

		// Return list of Datas
		logger.info("leaving findAll()");
		return datas;
		
	}
	@Override
	public WeatherData findById(int id) {
		logger.info("findById() called");
		WeatherData data = new WeatherData();
				try {
					// Connect to the Database
					conn = DriverManager.getConnection(url, username, password);

					// Execute SQL Query and loop over result set
					String sql1 = String.format("SELECT * FROM WEATHERDATA WHERE WEATHER_ID='%S'",id);
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery(sql1);
					//PiData(double tempF, double tempC, double humidity, double pressure)
					while(rs1.next()) {
					data = new WeatherData(rs1.getDouble("TEMPF"),rs1.getDouble("TEMPC"),rs1.getDouble("HUMIDITY"),rs1.getDouble("PRESSURE"));
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
				logger.info("leaving findById()");
				return data;
	}
	//to be implemented later
	@Override
	public WeatherData findBy(WeatherData pidata) {
		return null;
		
	}
	
	public List<WeatherData> findByDate(String str) {
		logger.info("findByDate()called");
		List<WeatherData> datas = new ArrayList<WeatherData>();
		try {
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query and loop over result set
			String sql1 = String.format("SELECT * FROM WEATHERDATA WHERE DATE='%S'",str);
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				WeatherData data = new WeatherData(rs1.getDouble("TEMPF"),rs1.getDouble("TEMPC"),rs1.getDouble("HUMIDITY"),rs1.getDouble("PRESSURE"));
				datas.add(data);
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

		// Return list of Datas
		logger.info("leaving findByDate()");
		return datas;
	}
	@Override
	public boolean create(WeatherData data) {

		logger.info("create() called");
		try {
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Insert WeatherData
			String sql1 = String.format("INSERT INTO WEATHERDATA(PRESSURE, TEMPC, TEMPF, HUMIDITY, DATE) VALUES('%s', '%s', '%s','%s','%s')",
					data.getPressure(),data.getTempC(),data.getTempF(),data.getHumidity(),data.getDate());
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
		logger.info("leaving create()");
		// Return OK
		return true;
	}
	//to be implemented later
	@Override
	public boolean update(WeatherData data) {
		return false;
	}
	//to be implemented later
	@Override
	public boolean delete(WeatherData data) {
		return false;
	}

}
