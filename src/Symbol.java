/*H*****************************************************************************
 * Filename: Symbol.java
 * Description: Specifies what symbols the operator can accept.
 * Modified: 2021-06-05	File created.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

public enum Symbol {
	DECIMAL ("."),
	NEGATIVE ("-"),
	EQUAL ("="),
	DELETE ("delete"),
	CLEAR ("clear");

	private final String symbol;

	Symbol(final String symbol) {
		this.symbol = symbol;
	}

	protected String getSymbol() {
		return symbol;
	}

	protected boolean isSymbol(final String symbol) {
		return this.symbol.equals(symbol);
	}
}
