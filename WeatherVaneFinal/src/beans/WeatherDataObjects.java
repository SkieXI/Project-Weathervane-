package beans;

import java.util.List;

/**
 * 
 * @author Jerran Fredricks and Joe Leon
 *
 */
public class WeatherDataObjects {
	// create a list of weatherdata objects
	private List<WeatherData> datas;

	// constructors
	public WeatherDataObjects(List<WeatherData> datas) {
		super();
		this.datas = datas;
	}

	public WeatherDataObjects() {
		super();
		this.datas = null;
	}

	// getters and setters
	public List<WeatherData> getDatas() {
		return datas;
	}

	public void setDatas(List<WeatherData> datas) {
		this.datas = datas;
	}

}
