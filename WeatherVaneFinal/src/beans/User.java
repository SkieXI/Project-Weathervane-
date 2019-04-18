package beans;
/**
 * @Author Jerran Fredricks/Joe Lean
 * 10/21/2018
 **/
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class User
{
	/*
	 * Fields:
	 * userName
	 * Email
	 * password
	 * firstName
	 * lastName
	 * permission
	 */
	
	@NotNull(message="Please enter a username.")
	@Size(min=3, max=255, message ="Usernames must be 3 characters long.")
	private String userName;
	
	@NotNull(message="Please enter a correct email address.")
	@Size(min=3, max=255, message ="Email must be 3 characters long.")
	private String email;
	
	@NotNull(message="Please enter a password.")
	@Size(min=3, max=255, message ="password must be 3 characters long.")
	private String password;
	
	@NotNull(message="Please enter your first name.")
	@Size(min=3, max=255, message ="Your name must be 3 characters long.")
	private String firstName;
	
	@NotNull(message="Please enter your last name.")
	@Size(min=3, max=255, message ="Your last name must be 3 characters long.")
	private String lastName;
	
	@NotNull(message="???")
	@Size(min=3, max=255, message ="You should not be allowed to see this. Shoo shoo!")
	private int permissions;
	
	//Constructors
	public User() 
	{
		userName ="";
		email = "";
		password = "";
		firstName = "";
		lastName = "";
		permissions = 0;
	}
	
	public User(String userName, String email, String password, String firstName, String lastName, int permissions)
	{
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.permissions = permissions;
	}
	
	//Getters and Setters
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPermissions() {
		return permissions;
	}
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	
}
