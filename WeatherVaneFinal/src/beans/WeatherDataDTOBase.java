package beans;

/**
 * 
 * @author Jerran Fredricks and Joe Leon
 *
 */
public class WeatherDataDTOBase {
	private int status;
	private String message;

	// constructors
	public WeatherDataDTOBase(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public WeatherDataDTOBase() {
		super();
		this.status = 0;
		this.message = "";
	}

	// getters and setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
