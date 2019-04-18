package beans;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author Jerran Fredricks/Joe Leon
 * 10/21/2018
 **/
public class WeatherData
{

	//Variables captured by the Pi.
	private int weatherID;
	private double tempF;
	private double tempC;
	private double humidity;
	private double pressure;
	private String dateStr; 
	//Constructor for Data.
	public WeatherData()
	{
		weatherID = 0;
		tempF = 0.0;
		tempC = 0.0;
		humidity = 0.0;
		pressure = 0.0;
		dateStr = this.getDate();
	}
	
	public WeatherData(double tempF, double tempC, double humidity, double pressure) {
		super();
		this.tempF = tempF;
		this.tempC = tempC;
		this.humidity = humidity;
		this.pressure = pressure;
		this.dateStr = this.getDate();
	}
	//Getters and Setters
	public int getWeatherID() {
		return weatherID;
	}
	public void setWeatherID(int id) {
		this.weatherID = id;
	}
	public double getTempF() {
		return tempF;
	}
	public void setTempF(double tempF) {
		this.tempF = tempF;
	}
	public double getTempC() {
		return tempC;
	}
	public void setTempC(double tempC) {
		this.tempC = tempC;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public void setDateStr(String date) {
		this.dateStr = date;
	}
	public String getDateStr() {
		return dateStr;
	}
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());
		return date;
	}
	//convert weather object toString
	public String toString() {
		return "{pressure: "+this.pressure+" tempC: "+this.tempC+ " tempF: "+this.tempF+" humidity: "+this.humidity+"}";
	}
}
