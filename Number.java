/*H*****************************************************************************
 * Filename: Number.java
 * Description: Specifies what single digit numbers the calculator can accept.
 * Modified: 2021-06-05	File created.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

public enum Number {
	ZERO ("0"),
	ONE ("1"),
	TWO ("2"),
	THREE ("3"),
	FOUR ("4"),
	FIVE ("5"),
	SIX ("6"),
	SEVEN ("7"),
	EIGHT ("8"),
	NINE ("9");

	private String number;

	Number(String number) {
		this.number = number;
	}

	protected String getNumber() {
		return number;
	}

	protected boolean isNumber(final String number) {
		return this.number.equals(number);
	}
}

