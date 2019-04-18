package util;

import beans.WeatherData;
import beans.WeatherDataDTO;
import beans.WeatherDataDTOInterface;
/**
 * @Author Jerran Fredricks/Joe Lean
 **/
public class DataFactory {
	//use getUser method to get object of type shape 
	   public WeatherDataDTOInterface getWeatherInterface(String weatherDataType){
	      if(weatherDataType == null){
	    	  return new WeatherDataDTO(-2,"NUll weatherDataType",new WeatherData());
	      }	
	      //if value is "DATAMISSING return a WeatherDTO with -1 and string Data Not Found as well as default weatherData
	      if(weatherDataType.equalsIgnoreCase("DATAMISSING")){
	         return new WeatherDataDTO(-1,"Weather Data Not Found",new WeatherData());
	      }
	      //if value is DATAFOUND return WeatherDTO with 1 and string DataFound as well as weatherData
	      if(weatherDataType.equalsIgnoreCase("DATAFOUND")){
		         return new WeatherDataDTO(0,"Weather Data Found",new WeatherData());
		      }
	      return new WeatherDataDTO(-2,"Unkown weatherDataType",new WeatherData());
	   }
	 
}
