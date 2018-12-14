package application;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class SnacksVendingMachine extends GenericVendingMachine {	
	
	private static final Product[] soldProductsArray = {
			new Product ("Mars", new BigDecimal ("1")),
			new Product ("Bounty", new BigDecimal ("1")),
			new Product ("Snickers", new BigDecimal ("1")),
			new Product ("Chips", new BigDecimal ("0.7")),
			new Product ("Twix", new BigDecimal ("1")),
			new Product ("M&M's", new BigDecimal ("1.2")),
			new Product ("Lion", new BigDecimal ("1")),
			};
	
	private static final ArrayList<Product> soldProducts = new ArrayList<Product>(Arrays.asList(soldProductsArray));
		
	private static final ArrayList<Coin> acceptedCoins = new ArrayList<Coin>(Arrays.asList(DollarCoin.values()));
	
	private static final String title = "Snacks Vending Machine";
	
	public SnacksVendingMachine () {
		super(soldProducts, acceptedCoins, title);
		
	}		
}
