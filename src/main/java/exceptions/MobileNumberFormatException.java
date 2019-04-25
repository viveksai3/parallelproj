package exceptions;

public class MobileNumberFormatException extends Exception{

	public MobileNumberFormatException() {
		System.err.println("Mobile number format is incorrect");
	}
}
