package TestApp.RestTest.Exceptions;

public class DataNotFoundException extends RuntimeException{

	public DataNotFoundException(String msg)
	{
		super(msg);
	}
}
