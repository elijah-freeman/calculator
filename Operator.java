/*H*****************************************************************************
 * Filename: Operator.java
 * Description: Specifies what operators the calculator can accept.
 * Modified: 2021-06-05	File created.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

public enum Operator {
	EXPONENTIATION ("^"),
	MULTIPLICATION ("*"), 
	DIVISION ("/"),
	ADDITION ("+"),
	SUBTRACTION ("-");

	private final String operator;

	Operator(String operator) {
		this.operator = operator;
	}

	protected String getOperator() {
		return operator;
	}

	protected boolean isOperator(final String operator) {
		return this.operator.equals(operator);
	}
}
