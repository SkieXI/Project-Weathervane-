package beans;

/**
 * 
 * @author Jerran Fredricks and Joe Leon
 *
 */
public interface WeatherDataDTOInterface {
	public WeatherData getData();

	public void setData(WeatherData data);

	public int getStatus();

	public void setStatus(int status);

	public String getMessage();

	public void setMessage(String message);
}
