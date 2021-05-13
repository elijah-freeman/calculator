/*H*****************************************************************************
 * Filename: Calculator.java
 * Description: Defines a calculator class 
 * Comment:
 * Modified: 2021-05-11	File Created.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import java.util.*;

/**
 * Defines a Calculator that can perform the following calculations: multiplication,
 * addition, subtraction, exponentiation. Calculator can also handle paranthesis, 
 * and operates in PEMDAS fashion.
 */
public class Calculator {

	/**
	 * Buffer than contains the inputted operators and operands.
	 */
	private Deque<Double> buffer;

	/**
	 * Constructs calculator object. Initializes buffer.
	 */
	public Calculator() {
		buffer = new ArrayDeque<Integer>();
	}

	/**
	 * Adds an element to the buffer.
	 */
	public void addElement();

	/**
	 * Removes all elements from the buffer.
	 */
	private void clearBuffer();

	/**
	 * TODO may remove.
	 */
	public void calculate();

	/**
	 * Checks precendence of operand in order of PEMDAS.
	 */
	private void checkPrecedence();

	/**
	 * Checks if element is a parenthesis.
	 */
	private boolean isParenthesis();

	/**
	 * Checks if operand specifies mulitiplication.
	 */
	private boolean isMultiplication();

	/**
	 * Checks if operand specifies division.
	 */
	private boolean isDivision();

	/**
	 * Checks if operand specifies exponentiation.
	 */
	private boolean isExponent();

	/**
	 * Checks if operand specifies multiplication..
	 */
	private double multiply();

	/**
	 * Checks if operand specifies multiplication.
	 */
	private double divide();

	/**
	 * Checks if operand specifies addition.
	 */
	private double add();

	/**
	 * Checks if operand specifies subtraction.
	 */
	private double subtract();

	/**
	 * Checks if operand specifies exponentiation.
	 */
	private double exponentiate();

	/**
	 * Returns the final result of the calculation.
	 */
	public double getResult();
}
