package application;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public interface VendingMachine {
	
	public ArrayList<Coin> buyProduct (Product p) throws NotEnoughMoneyException, ProductNotAvailableException, MissingMoneyException;
	
	public boolean remains (Product p);
	
	public boolean remains (Coin c);
	
	public int getQuantity (Product p);
	
	public int getQuantity (Coin c);
	
	public ArrayList<Product> soldProducts();
	
	public ArrayList<Coin> acceptedCoins();
	
	public void insertCoin(Coin c);
	
	public Map<Coin, Integer> giveBackMoney();
	
	public BigDecimal insertedMoney();
	
	public void fill (Product p, int q);
	
	public void fillCoin (Coin c, int q);
	
}
