package application;
import java.math.BigDecimal;

public class Product {
	
	private String name;
	private BigDecimal price;	
	
	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() { return this.name; }
	
	public BigDecimal getPrice() { return this.price; } 
			
	public String toString() { return this.name+" "+this.price+"€"; }
	
	public boolean equals(Object o) { 
		if (this.name == ((Product) o).name && this.price == ((Product) o).price) 
			return true;
		else return false;
		}

}
