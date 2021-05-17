/*H*****************************************************************************
 * Filename: Calculator.java
 * Description: Defines a calculator class 
 * Comment:
 * Modified: 2021-05-11	File Created.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import java.util.*;

/**
 * Defines a Calculator that can perform the following calculations: 
 * multiplication, addition, subtraction, exponentiation. Calculator can also 
 * handle paranthesis, and operates in PEMDAS fashion.
 */
public class Calculator {

	private static final int NUMBER_OF_OPERATORS = 6;

	/**
	 * Buffer than contains the inputted operators and operands.
	 */
	private Deque<String> buffer;

	/** 
	 * Most recent operator.
	 */
	private String recentOperator;

	/**
	 * List of operators.
	 */
	private String[] operators;

	/**
	 * Constructs calculator object. Initializes buffer.
	 */
	public Calculator() {
		buffer = new ArrayDeque<String>();
		this.operators = new String[NUMBER_OF_OPERATORS];
		this.operators[0] = ")";
		this.operators[1] = "^";
		this.operators[2] = "*";
		this.operators[3] = "/";
		this.operators[4] = "+";
		this.operators[5] = "-";
	}

	/**
	 * Adds an element to the buffer.
	 */
	public void addElement(String element) {
		this.buffer.addFirst(element);
		if (!isOperator(element) && buffer.size() > 2) {
			checkPrecedence(this.recentOperator); 
		}
	}

	/**
	 * Removes all elements from the buffer.
	 */
	private void clearBuffer() {
		while (this.buffer.size() > 0) {
			this.buffer.removeFirst();
		}
	}

	/**
	 * TODO may remove.
	 */
	public void calculate() {
		
	}

	/**
	 * Checks precendence of operand in order of PEMDAS.
	 * TODO may need to remove isSubtract() and isAddition check.
	 */
	private void checkPrecedence(String operator) {
		final double secondOperand = Double.parseDouble(this.buffer.removeFirst());
		this.buffer.removeFirst();
		final double firstOperand = Double.parseDouble(this.buffer.removeFirst());
		if (isParenthesis(operator)) {
			//TODO define what to do.
		} else if (isExponentiation(operator)) {
			exponentiate(firstOperand, secondOperand);
		} else if (isMultiplication(operator)) {
			multiply(firstOperand, secondOperand);
		} else if (isDivision(operator)) {
			divide(firstOperand, secondOperand); 
		} else if (isAddition(operator)) {
			add(firstOperand, secondOperand);
		} else if (isSubtraction(operator)) {
			subtract(firstOperand, secondOperand);
		}
	}

	/**
	 * Checks if element is an operator. If true then the operator is stored.
	 */
	private boolean isOperator(String element) {
		for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
			if (element.equals(operators[i])) {
				this.recentOperator = element;
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if element is a parenthesis.
	 * @return boolean, true if element is a closing paranthesis. False otherwise.
	 */
	private boolean isParenthesis(String element) {
		return element.equals(")");
	}

	/**
	 * Checks if operand specifies exponentiation.
	 * @return boolean, true if operand specifies exponentiation. False otherwise.
	 */
	private boolean isExponentiation(String element) {
		return element.equals("^");
	}

	/**
	 * Checks if operand specifies mulitiplication.
	 * @return boolean, true if operand specifies multiplicatin. False otherwise.
	 */
	private boolean isMultiplication(String element) {
		return element.equals("*");
	}

	/**
	 * Checks if operand specifies division.
	 * @return boolean, true if operand specifies division. False otherwise.
	 */
	private boolean isDivision(String element) {
		return element.equals("/");
	}

	/**
	 * Checks if operand specifies addition.
	 * @return boolean, true if operand specifies addition. False otherwise.
	 */
	private boolean isAddition(String element) {
		return element.equals("+");
	}

	/**
	 * Checks if operand specifies subtraction.
	 * @return boolean, true if operand specifies subtraction. False otherwise.
	 */
	private boolean isSubtraction(String element) {
		return element.equals("-");
	}

	/**
	 * Checks if operand specifies multiplication..
	 * @return double, true if operand specifies multiplication. False otherwise.
	 */
	private void multiply(double multiplier, double multiplicand) {
		final double result = multiplier * multiplicand;
		this.buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies multiplication.
	 * @return double, true if operand specifies division. False otherwise.
	 */
	private void divide(double dividend, double divisor) {
		final double result = dividend / divisor;
		this.buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies addition.
	 * @return double, true if operand specifies addition. False otherwise.
	 */
	private void add(double firstSummand, double secondSummand) {
		final double result = firstSummand + secondSummand;
		this.buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies subtraction.
	 * @return double, true if operand specifies subtraction. False otherwise.
	 */
	private void subtract(double minuend, double subtrahend) {
		final double result = minuend - subtrahend;
		this.buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies exponentiation.
	 * @return double, true if operand specifies exponentiation. False otherwise.
	 */
	private void exponentiate(double base, double exponent) {
		final double result = Math.pow(base, exponent);
		this.buffer.addFirst(Double.toString(result));
	}

	/**
	 * Returns the final result of the calculation.
	 * @return double, the final result of the calculation.
	 */
	public double getResult() {
		return Double.parseDouble(this.buffer.removeFirst());
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		System.out.println("Calculator Class\n");
		calculator.addElement("5");
		calculator.addElement("+");
		calculator.addElement("5");
		calculator.addElement("-");
		calculator.addElement("1");
		System.out.printf("Result: %f\n", calculator.getResult());
	}
}
