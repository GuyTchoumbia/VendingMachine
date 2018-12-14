package application;
import java.math.BigDecimal;

public enum DollarCoin implements Coin {
	
	ONE(new BigDecimal(1)), 
	FIFTYCENTS(new BigDecimal("0.5")), 
	TWENTYFIVECENTS(new BigDecimal("0.25")), 
	TENCENTS(new BigDecimal("0.1")), 
	FIVECENTS(new BigDecimal("0.05")), 
	ONECENT(new BigDecimal("0.01"));

	
	private final BigDecimal value;
	
	private DollarCoin(BigDecimal v) {
		this.value = v;				
	}

	@Override
	public BigDecimal getValue() { return this.value; }
	
	@Override
	public String toString() { return this.value+"$"; }
	
}
