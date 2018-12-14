package application;

@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {
	
	public NotEnoughMoneyException() {
		super ("Not enoughmoney to give back change");
	}

}
