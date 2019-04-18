package business;

import beans.WeatherData;
import beans.WeatherDataObjects;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.User;
import data.UserDataAccessInterface;
import data.WeatherDataAccessInterface;
import util.LoggingIntercepter;

/**
 * @Author Jerran Fredricks/Joe Lean 10/21/2018
 **/
@Stateless
@Local(WeatherManagerInterface.class)
@LocalBean
@Interceptors(LoggingIntercepter.class)
public class WeatherManager implements WeatherManagerInterface {

	@EJB
	UserDataAccessInterface<User> userDao;
	@EJB
	WeatherDataAccessInterface<WeatherData> weatherDao;

	Logger logger = LoggerFactory.getLogger(WeatherManager.class);

	public WeatherManager(UserDataAccessInterface<User> userDao, WeatherDataAccessInterface<WeatherData> weatherDao) {
		this.userDao = userDao;
		this.weatherDao = weatherDao;
	}

	public WeatherManager() {
	}

	/*
	 * This code is for user data
	 */
	/**
	 * This method is for seeing if the user logging in is in the database
	 * 
	 * @param User user
	 * @return boolean
	 */
	@Override
	public boolean validateLogin(User user) {
		logger.info("validateLogin called");
		if (!userDao.findBy(user).getEmail().equals(user.getEmail())) {
			return false;
		} else
			return true;
	}

	/**
	 * * This method is for registering an user to the database
	 * 
	 * @param User user
	 * @return user
	 */
	@Override
	public User validateRegister(User user) {
		logger.info("validateRegister called");
		userDao.create(user);
		logger.info("User registered into database");
		return user;
	}

	/**
	 * pass in an ID and return the data that is linked to that id in the database
	 * 
	 * @param Int id
	 * @return data
	 */
	public WeatherData byId(int id) {
		logger.info("weatherData called with ID value of: " + id);
		WeatherData data = weatherDao.findById(id);
		return data;
	}

	/**
	 * get all of data from the database
	 * 
	 * @param N/A
	 * @return dataObjects
	 */
	public WeatherDataObjects getAllData() {
		logger.info("getAllData called");
		WeatherDataObjects dataObjects = new WeatherDataObjects();
		dataObjects.setDatas(weatherDao.findAll());
		return dataObjects;
	}

	/**
	 * put data into the database
	 * 
	 * @param WeatherData data
	 * @return data
	 */
	@Override
	public WeatherData putDataIntoDatabase(WeatherData data) {
		logger.info("putDataIntoDatabase called with data: " + data);
		weatherDao.create(data);
		return data;
	}

	/**
	 * get all of todays data from the database
	 * 
	 * @param WeatherDataObjects dataObjects, String str
	 * @return dataObjects
	 */
	public WeatherDataObjects getDaysData(WeatherDataObjects dataObjects, String str) {
		logger.info("getDaysData called with date: " + str);
		dataObjects.setDatas(weatherDao.findByDate(str));
		return dataObjects;
	}

}
