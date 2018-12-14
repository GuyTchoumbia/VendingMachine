package application;
import java.math.BigDecimal;

public enum EuroCoin implements Coin {
	TWO(new BigDecimal(2)), 
	ONE(new BigDecimal(1)), 
	FIFTYCENTS(new BigDecimal("0.5")), 
	TWENTYCENTS(new BigDecimal("0.2")), 
	TENCENTS(new BigDecimal("0.1")), 
	FIVECENTS(new BigDecimal("0.05")), 
	TWOCENTS(new BigDecimal("0.02")), 
	ONECENT(new BigDecimal("0.01"));

	
	private final BigDecimal value;
	
	private EuroCoin(BigDecimal v) {
		this.value = v;				
	}

	@Override
	public BigDecimal getValue() { return this.value; }
	
	@Override
	public String toString() { return this.value+"€"; }
	
}
