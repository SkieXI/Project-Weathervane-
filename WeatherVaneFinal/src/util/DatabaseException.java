package util;
/**
 * @Author Jerran Fredricks/Joe Lean
 * Barrowed Code From Assignment 6
 * 10/21/2018
 **/
public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 8749912411505566655l;

	public DatabaseException(Throwable e) {
		super(e);
	}
}
