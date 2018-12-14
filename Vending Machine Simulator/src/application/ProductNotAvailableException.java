package application;

@SuppressWarnings("serial")
public class ProductNotAvailableException extends Exception {
	
	public  ProductNotAvailableException() {
		super ("Product not available");
	}
}
