package application;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class DrinksVendingMachine extends GenericVendingMachine {	
	
	private static final Product[] soldProductsArray = {
			new Product ("Coca - Cola", new BigDecimal ("0.6")),
			new Product ("Fanta", new BigDecimal ("0.6")),
			new Product ("Canada Dry", new BigDecimal ("0.7")),
			new Product ("Nestea", new BigDecimal ("0.7")),
			new Product ("Schweppes Agrum", new BigDecimal ("0.8"))
			};
	
	private static final ArrayList<Product> soldProducts = new ArrayList<Product>(Arrays.asList(soldProductsArray));
		
	private static final ArrayList<Coin> acceptedCoins = new ArrayList<Coin>(Arrays.asList(EuroCoin.values()));
	
	private static final String title = "Drinks Vending Machine";
	
	public DrinksVendingMachine () {
		super(soldProducts, acceptedCoins, title);
		
	}
	
}


