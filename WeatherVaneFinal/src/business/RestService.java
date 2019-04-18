package business;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.*;
import util.*;

//SLF4L logger imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;

/**
 * @Author Jerran Fredricks/Joe Lean 10/21/2018
 **/
@RequestScoped
@Path("/weather")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
@Interceptors(LoggingIntercepter.class)
public class RestService {

	DataFactory dFactory = new DataFactory();

	@EJB
	WeatherManagerInterface service;

	Logger logger = LoggerFactory.getLogger(RestService.class);

	/**
	 * Rest service call to get data with certain id from database
	 * 
	 * @param Int id
	 * @return weather data with id equal to id passed in
	 */
	@GET
	@Path("/getdata/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public WeatherDataDTOInterface getData(@PathParam("id") int id) {
		logger.info("getData() called for data with ID:" + id);

		WeatherData data = new WeatherData();
		try {
			data = service.byId(id);
			WeatherDataDTOInterface response = dFactory.getWeatherInterface("DATAFOUND");
			response.setData(data);
			logger.info(0 + " Weather Data Found:" + data);
			return response;
		} catch (Exception e) {
			WeatherDataDTOInterface response = dFactory.getWeatherInterface("DATAMISSING");
			logger.error(-1 + " Data not Found:" + data);
			return response;
		}
	}

	/**
	 * Rest Service to get all data from database
	 * 
	 * @param N/A
	 * @return all of todays weather data
	 */
	@GET
	@Path("/getalldata")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WeatherData> getAllData() {
		logger.info("getalldata Called");

		// get todays date
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		// format the date to string
		String date = sdf.format(new Date());
		// create WeatherDataObjects to hold the weather data
		WeatherDataObjects dataObjects = new WeatherDataObjects();
		// pass WeatherdataObjects and date into service
		dataObjects = service.getDaysData(dataObjects, date);
		// return a list of weather Datas
		List<WeatherData> datas = dataObjects.getDatas();
		return datas;
	}

	/**
	 * Rest Service method to get json data from an IoT device to be pushed to a
	 * database
	 * 
	 * @param data from a external IoT Device
	 * @return N/A
	 */
	@POST
	@Path("/data")
	@Consumes("application/json")
	@Produces("application/json")
	public void setData(WeatherData data) {

		logger.info("Data Recieved:" + data);

		// get Json value and store it into WeatherData Object
		try {
			// put data in database
			service.putDataIntoDatabase(data);
			logger.info("Data Successfully put into Database");
		} catch (Exception e) {
			logger.error("An error occured putting data into database");
		}
	}

}