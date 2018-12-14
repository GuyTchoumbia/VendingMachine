package application;

@SuppressWarnings("serial")
public class MissingMoneyException extends RuntimeException {
	
	public MissingMoneyException() {
		super("Missing Money, insert more coins");
	}
}
