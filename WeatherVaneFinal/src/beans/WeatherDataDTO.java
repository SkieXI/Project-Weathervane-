package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Jerran Fredricks and Joe Leon
 *
 */
@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherDataDTO extends WeatherDataDTOBase implements WeatherDataDTOInterface {
	// get weatherdata
	private WeatherData data;

	// constructor
	public WeatherDataDTO(int status, String message, WeatherData data) {
		super(status, message);
		this.data = data;
	}

	public WeatherDataDTO() {
		super(0, "");
		this.data = null;
	}

	// getters and setters
	@Override
	public WeatherData getData() {
		return data;
	}

	@Override
	public void setData(WeatherData data) {
		this.data = data;
	}

}