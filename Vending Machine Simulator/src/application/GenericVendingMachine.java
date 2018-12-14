package application;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericVendingMachine implements VendingMachine {
		
	private final ArrayList<Coin> acceptedCoins;
	private final ArrayList<Product> soldProducts;
	
	private Map<Coin, Integer> coinsQuantities;
	private Map<Product, Integer> productQuantities;
	private Map<Coin, Integer> insertedCoins;
	
	private String title;
		
	public GenericVendingMachine(ArrayList<Product> products, ArrayList<Coin> coins, String title) {
		
		this.soldProducts = products;
				
		this.productQuantities = new HashMap<>();
		for (Product p : soldProducts) {
			this.productQuantities.put(p, 10);
		}
		
		this.acceptedCoins = coins;
				
		this.coinsQuantities = new HashMap<>();
		for (Coin c : acceptedCoins) {
			this.coinsQuantities.put(c, 10);
		}
		
		this.insertedCoins = new HashMap<>();
		
		this.title = title;
	}
	
	//computeChange
	@SuppressWarnings("null")
	public ArrayList<Coin> computeChange (BigDecimal back) throws NotEnoughMoneyException {
		ArrayList<Coin> change = new ArrayList<Coin>();
		BigDecimal totalMoney = new BigDecimal(0);		
		for (Coin c : acceptedCoins) {
			System.out.println("calculating total Money");
			System.out.println(getQuantity(c)+" coins of "+c.getValue());
			totalMoney = totalMoney.add(c.getValue().multiply(new BigDecimal(getQuantity(c))));			
		}
		System.out.println("Total Money in the bank: "+totalMoney.toString());		
		if ((totalMoney.subtract(back).signum()) < 0)
			throw new NotEnoughMoneyException();
		else {
			for (Coin c : acceptedCoins) {
				while (remains(c) && back.subtract(c.getValue()).signum() >= 0) {
					change.add(c);
					coinsQuantities.replace(c, coinsQuantities.get(c)-1);					
					back = back.subtract(c.getValue());					
				}
			}
		}		
		return change;		
	}
	
	//inutilisé
	public Product checkProduct (Product p) throws IllegalArgumentException{
		try { 
			(soldProducts).contains(p);
		}
		catch (IllegalArgumentException e) {
			System.out.println(p.toString()+" is not sold by the machine");
		}		
		return p;
	}
	
	//inutilisé
	public int checkCoin (Coin c) throws IllegalArgumentException {
		int index = -1;
		try {
			index = acceptedCoins.indexOf(c);			
		}
		catch (IllegalArgumentException e) {
			System.out.println(c.toString()+" coin is not accepted by the machine");
		}
		return index;
	}
	
	//buyProduct
	@Override
	public ArrayList<Coin> buyProduct(Product p)	throws NotEnoughMoneyException, ProductNotAvailableException, MissingMoneyException {
		BigDecimal change = insertedMoney().subtract(p.getPrice());
		if (remains(p)) {			
			if (change.signum() >= 0) {
				ArrayList<Coin> changeCoins = computeChange(change);
				fill(p, getQuantity(p)-1);
				insertedCoins.clear();
				return changeCoins;
			}
			else throw new MissingMoneyException();
		}
		else throw new ProductNotAvailableException();		
	}
	
	//mostly accessors and casters after this

	@Override
	public boolean remains(Product p) {
		if (getQuantity(p) > 0)
			return true;
		else return false;
	}

	@Override
	public boolean remains(Coin c) {
		if (getQuantity(c) > 0)
			return true;
		else return false;
	}

	@Override
	public int getQuantity(Product p) {
				return productQuantities.get(p);		
	}
	
	@Override
	public int getQuantity(Coin c) {
		return coinsQuantities.get(c);		
	}

	@Override
	public ArrayList<Product> soldProducts() {
		return soldProducts;
	}

	@Override
	public ArrayList<Coin> acceptedCoins() {
		return acceptedCoins;
	}

	@Override
	public void insertCoin(Coin c) {
		if (insertedCoins.containsKey(c))
			insertedCoins.replace(c, insertedCoins.get(c)+1);
		else 
			insertedCoins.put(c,  1);
	}

	@Override
	public Map<Coin, Integer> giveBackMoney() {
		return insertedCoins;
	}

	@Override
	public BigDecimal insertedMoney() {
		BigDecimal insertedMoney = new BigDecimal(0); 
		for (Map.Entry<Coin, Integer> entry : insertedCoins.entrySet()) {
			insertedMoney = insertedMoney.add(entry.getKey().getValue().multiply(new BigDecimal(entry.getValue())));
		}
		return insertedMoney;
	}

	@Override
	public void fill(Product p, int q) {
		productQuantities.replace(p, q);
	}

	@Override
	public void fillCoin(Coin c, int q) {
		coinsQuantities.replace(c, q);
	}

	public String getTitle() { return this.title; }	
	
}
