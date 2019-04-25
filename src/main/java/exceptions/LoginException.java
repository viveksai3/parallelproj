package exceptions;

public class LoginException extends Exception {

	public LoginException()
	{
	System.err.println("Invalid user name and password");
	}
}
