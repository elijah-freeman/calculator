/*H*****************************************************************************
 * Filename: Calculator.java
 * Description: Defines a calculator class 
 * Comment: Paranthesis implementation is not complete.
 * Modified: 2021-05-11	File Created.
 * 	     2021-05-17 Finished implementation w/o paranthesis.
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
	 * Buffer that contains the operators and operands.
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
	 * Constructs calculator object. Initializes buffer and sets the official
	 * list of acceptable operators.
	 */
	public Calculator() {
		buffer = new ArrayDeque<String>();
		operators = new String[NUMBER_OF_OPERATORS];
		operators[0] = ")";
		operators[1] = "^";
		operators[2] = "*";
		operators[3] = "/";
		operators[4] = "+";
		operators[5] = "-";
	}

	/**
	 * Adds an element to the buffer. If element is not and operator and
	 * more than two elements in a buffer then check precedence of the
	 * most recent operator.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 */
	public void addElement(String element) {
		buffer.addFirst(element);
		if (!isOperator(element) && buffer.size() > 2 || isParenthesis(element)) {
			checkParenthesisExponents(recentOperator); 
		}
	}

	/**
	 * Checks if element is an operator. If true then the operator is saved.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 */
	private boolean isOperator(String element) {
		for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
			if (element.equals(operators[i])) {
				recentOperator = element;
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO Parathesis implementation is not finished.
	 */
	private void checkParenthesisExponents(String operator) {
		if (isParenthesis(operator)) {
			buffer.removeFirst();
			String element = buffer.removeFirst();
			while (!element.equals(")")) {
				if (!isOperator(element)) {
					final double secondOperand = Double.parseDouble(element);
//					final String operator = buffer.removeFirst();
					if (isExponentiation(operator)) {
						final double firstOperand = Double.parseDouble(buffer.removeFirst());
						exponentiate(firstOperand, secondOperand);
					} else {
						execute();
					}

				}
			}
				
		} else if (isExponentiation(operator)) {
			final double secondOperand = Double.parseDouble(buffer.removeFirst());
			buffer.removeFirst();
			final double firstOperand = Double.parseDouble(buffer.removeFirst());
			exponentiate(firstOperand, secondOperand);
		} 
	}

	/**
	 * Checks if element is a parenthesis.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 * @return          true if element is a closing paranthesis. False otherwise.
	 */
	private boolean isParenthesis(String element) {
		return element.equals(")");
	}

	/**
	 * Checks if operand specifies exponentiation.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 * @return          true if operand specifies exponentiation. False otherwise.
	 */
	private boolean isExponentiation(String element) {
		return element.equals("^");
	}

	/**
	 * Checks if operand specifies mulitiplication.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 * @return          true if operand specifies multiplicatin. False otherwise.
	 */
	private boolean isMultiplication(String element) {
		return element.equals("*");
	}

	/**
	 * Checks if operand specifies division.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 * @return          true if operand specifies division. False otherwise.
	 */
	private boolean isDivision(String element) {
		return element.equals("/");
	}

	/**
	 * Checks if operand specifies addition.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 * @return          true if operand specifies addition. False otherwise.
	 */
	private boolean isAddition(String element) {
		return element.equals("+");
	}

	/**
	 * Checks if operand specifies subtraction.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 * @return          true if operand specifies subtraction. False otherwise.
	 */
	private boolean isSubtraction(String element) {
		return element.equals("-");
	}

	/**
	 * Checks if operand specifies exponentiation.
	 * 
	 * @param  base      the base that the exponent is to operate on.
	 * @param  exponent  the power that the base is raised too.
	 * @return           true if operand specifies exponentiation. False otherwise.
	 */
	private void exponentiate(double base, double exponent) {
		final double result = Math.pow(base, exponent);
		buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies multiplication..
	 *
	 * @return  true if operand specifies multiplication. False otherwise.
	 */
	private void multiply(double multiplier, double multiplicand) {
		final double result = multiplier * multiplicand;
		buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies multiplication.
	 *
	 * @return  true if operand specifies division. False otherwise.
	 */
	private void divide(double dividend, double divisor) {
		final double result = dividend / divisor;
		buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies addition.
	 *
	 * @return  true if operand specifies addition. False otherwise.
	 */
	private void add(double firstSummand, double secondSummand) {
		final double result = firstSummand + secondSummand;
		buffer.addFirst(Double.toString(result));
	}

	/**
	 * Checks if operand specifies subtraction.
	 *
	 * @return  true if operand specifies subtraction. False otherwise.
	 */
	private void subtract(double minuend, double subtrahend) {
		final double result = minuend - subtrahend;
		buffer.addFirst(Double.toString(result));
	}

	/**
	 * Returns the final result of the calculation.
	 *
	 * @return  the final result of the calculation.
	 */
	public double getResult() {
		execute();
		return Double.parseDouble(buffer.removeFirst());
	}

	private void execute() {
		Deque<String> temp = new ArrayDeque<String>();
		while (buffer.size() > 0) {
			final String element = buffer.removeFirst();
			if (isOperator(element)) {
				if (isMultiplication(element)) {
					final double secondOperand = Double.parseDouble(buffer.removeFirst());
					final double firstOperand = Double.parseDouble(temp.removeFirst());
					multiply(firstOperand, secondOperand);
				} else if (isDivision(element)) {
					final double secondOperand = Double.parseDouble(buffer.removeFirst());
					final double firstOperand = Double.parseDouble(temp.removeFirst());
					divide(firstOperand, secondOperand);
				} else {
					temp.addFirst(element);
				}
			} else {
				temp.addFirst(element);
			}
		}
		while (temp.size() > 0) {
			final String element = temp.removeFirst();
			if (isOperator(element)) {
				final double secondOperand = Double.parseDouble(temp.removeFirst());
				final double firstOperand = Double.parseDouble(buffer.removeFirst());
				if (isAddition(element)) {
					add(firstOperand, secondOperand);
				} else if (isSubtraction(element)) {
					subtract(firstOperand, secondOperand);
				}
			} else {
				buffer.addFirst(element);
			}
		}
	}

	/**
	 * Removes all elements from the buffer.
	 */
	private void clearBuffer() {
		while (buffer.size() > 0) {
			buffer.removeFirst();
		}
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		System.out.println("Calculator Class\n");
		calculator.addElement("5");
		calculator.addElement("+");
		calculator.addElement("2");
		calculator.addElement("^");
		calculator.addElement("3");
		calculator.addElement("+");
		calculator.addElement("5");
		calculator.addElement("*");
		calculator.addElement("3");
		calculator.addElement("-");
		calculator.addElement("1");
		System.out.printf("Result: %f\n", calculator.getResult());
	}
}
