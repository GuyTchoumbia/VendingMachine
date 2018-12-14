package application;
import java.math.BigDecimal;

public enum YenCoin implements Coin {
	
	FIVEHUNDREDYENS(new BigDecimal("500")), 
	ONEHUNDREDYENS(new BigDecimal("100")), 
	FIFTYYENS(new BigDecimal("50")), 
	TENYENS(new BigDecimal("10")), 
	FIVEYENS(new BigDecimal("5")), 
	ONEYEN(new BigDecimal("1"));

	
	private final BigDecimal value;
	
	private YenCoin(BigDecimal v) {
		this.value = v;				
	}

	@Override
	public BigDecimal getValue() { return this.value; }
	
	@Override
	public String toString() { return this.value+"\u00A5"; }
	
}
