package business;

import beans.WeatherData;
import beans.WeatherDataObjects;
import beans.User;

/**
 * @Author Jerran Fredricks/Joe Lean 10/21/2018
 **/
public interface WeatherManagerInterface {
	// user data
	boolean validateLogin(User user);

	User validateRegister(User user);

	// data
	WeatherData putDataIntoDatabase(WeatherData data);

	WeatherData byId(int id);

	WeatherDataObjects getDaysData(WeatherDataObjects dataObjects, String date);
}
