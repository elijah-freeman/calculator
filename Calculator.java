/*H*****************************************************************************
 * Filename: Calculator.java
 * Description: Defines a calculator class. Calculator implemented using a
 * 		stack.
 * Comment: Parenthesis implementation is not complete.
 * Modified: 2021-05-11	File Created.
 * 	     2021-05-17 Finished implementation w/o parenthesis.
 * 	     2021-06-01 Refactored and using a map of ENUMs for operators
 * 	     		and digits.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Defines a calculator that can perform the following calculations: 
 * exponentiation, multiplication, division, addition, and subtraction. Calculator
 * can receive any number of expressions and carry out all the specified operations.
 * Expressions must be in infix order.
 */
public class Calculator {

	/**
	 * A stack that contains the input operators and operands.
	 */
	private final Deque<String> buffer;

	/**
	 * Constructs calculator object. Initializes buffer and sets the official
	 * list of acceptable operators.
	 */
	public Calculator() {
		buffer = new ArrayDeque<>();
	}

	/**
	 * Adds an element to the buffer. If element is not an operator and
	 * more than two elements in a buffer then check precedence of the
	 * most recent operator.
	 *
	 * @param  element  a string representing either an operator or an 
	 * 		    operand.
	 */
	public void addElement(final String element) {
		buffer.addFirst(element);
		checkIfMultiDigit();
	}

	/**
	 * Checks if the there are consecutive digits in the buffer. If true, 
	 * then the digits are joined to form a multi-digit number.
	 */
	private void checkIfMultiDigit() {
		if (buffer.size() > 1) {
			String currentElement = buffer.removeFirst();
			while (!isOperator(currentElement) && buffer.size() > 0) {
				String nextElement = buffer.removeFirst();
				if (isOperator(nextElement)) {
					buffer.addFirst(nextElement);
					break;
				} else {
					currentElement = combineDigits(nextElement, 
								currentElement);
				}
			}
			buffer.addFirst(currentElement);
		}
	}

	/**
	 * Combines two digits and returns the multi-digit result.
	 * 
	 * @param  firstDigit  the first digit in the multi-digit number.
	 * @param  secondDigit  the second digit in the multi-digit number.
	 * @return 		The newly formed multi-digit string.
	 */
	private String combineDigits(String firstDigit, final String secondDigit) {
		return firstDigit += secondDigit;
	}

	/**
	 * Checks if the argument is an operator.
	 *
	 * @param  element  the string to be checked.
	 * @return 	    true if element is an operator, false otherwise.
	 */
	private boolean isOperator(final String element) {
		return Stream.of(Operator.values()).anyMatch(value 
				-> element.equals(value.getOperator()));
	}

	/**
	 * Returns the final result of the calculation.
	 *
	 * @return  the final result of the calculation.
	 */
	public double getResult() {
		calculate();
		return Double.parseDouble(buffer.removeFirst());
	}

	/**
	 * Carries out all operations in buffer.
	 */
	private void calculate() {
		operateOnElements(this::isExponentiation, this::isExponentiation, 
						this::exponentiate, this::exponentiate);

		operateOnElements(this::isMultiplication, this::isDivision, 
						this::multiply, this::divide);

		operateOnElements(this::isAddition, this::isSubtraction, 
						this::add, this::subtract);
	}

	/**
	 * Carries out provided operations in order of precedence, either primary
	 * or secondary. 
	 *
	 * @param  isPrimaryOperation    a Predicate functional interface that determines
	 * 				 if the operator specifies the primary function.
	 * @param  isSecondaryOperation  a Predicate functional interface that determines 
	 * 				 if the operator specifies the secondary function.
	 * @param  primaryOperation	 a BiConsumer functional interface that specifies the 
	 * 				 higher order operation to be carried out.
	 * @param  secondaryOperation	 a BiConsumer functional interface that the specifies 
	 * 				 lower order operation to be carried out.
	 */
	private void operateOnElements(Predicate<String> isPrimaryOperation,
					Predicate<String> isSecondaryOperation,
					BiConsumer<Double, Double> primaryOperation,
					BiConsumer<Double, Double> secondaryOperation) {
		final Deque<String> temp = new ArrayDeque<>();
		while (buffer.size() > 0) {
			final String element = buffer.removeFirst();
			if (isOperator(element)) {
				if (isPrimaryOperation.test(element)) {
					final Double firstOperand = 
						Double.parseDouble(buffer.removeFirst());
					final Double secondOperand = 
						Double.parseDouble(temp.removeFirst());
					primaryOperation.accept(firstOperand, 
								secondOperand);
				} else if (isSecondaryOperation.test(element)) {
					final Double firstOperand = 
						Double.parseDouble(buffer.removeFirst());
					final Double secondOperand = 
						Double.parseDouble(temp.removeFirst());
					secondaryOperation.accept(firstOperand, 
								secondOperand);
				} else {
					temp.addFirst(element);
				}
			} else {
				temp.addFirst(element);
			}
		}
		while (temp.size() > 0) {
			buffer.addFirst(temp.removeFirst());
		}
	}

	/**
	 * Checks if provided element specifies exponentiation.
	 *
	 * @param  element  String to be checked.
	 * @return true if element specifies exponentiation, false otherwise.
	 */
	private boolean isExponentiation(final String element) {
		return Operator.EXPONENTIATION.isOperator(element);
	}

	/**
	 * Checks if provided element specifies multiplication..
	 *
	 * @param  element  String to be checked.
	 * @return true if element specifies multiplication, false otherwise.
	 */
	private boolean isMultiplication(final String element) {
		return Operator.MULTIPLICATION.isOperator(element);
	}

	/**
	 * Checks if provided element specifies division.
	 *
	 * @param  element  String to be checked.
	 * @return true if element specifies division, false otherwise.
	 */
	private boolean isDivision(final String element) {
		return Operator.DIVISION.isOperator(element);
	}

	/**
	 * Checks if provided element specifies addition.
	 *
	 * @param  element  String to be checked.
	 * @return true if element specifies addition, false otherwise.
	 */
	private boolean isAddition(final String element) {
		return Operator.ADDITION.isOperator(element);
	}

	/**
	 * Checks if provided element specifies subtraction.
	 *
	 * @param  element  String to be checked.
	 * @return true if element specifies subtraction, false otherwise.
	 */
	private boolean isSubtraction(final String element) {
		return Operator.SUBTRACTION.isOperator(element);
	}

	/**
	 * Carries out exponentiation using the provided base and exponent.
	 *
	 * @param  base      the base that is raised to the exponent power.
	 * @param  exponent  the exponent that the base is raised to. 
	 */
	private void exponentiate(final Double base, final Double exponent) {
		buffer.addFirst(Double.toString(Math.pow(base, exponent)));
	}

	/**
	 * Carries out multiplication using the provided multiplier and multiplicand.
	 *
	 * @param  multiplier    the number by which the multiplicand is multiplied.
	 * @param  multiplicand  the quantity that is multiplied by the multiplier. 
	 */
	private void multiply(final Double multiplier, final Double multiplicand) {
		buffer.addFirst(Double.toString(multiplier * multiplicand));
	}

	/**
	 * Carries out division using the provided dividend and divisor.
	 *
	 * @param  dividend  the dividend that is divided by the divisor.
	 * @param  divisor   the number that the dividend is divided by. 
	 */
	private void divide(final Double dividend, final Double divisor) {
		double result;
		try {
			result = dividend / divisor;
			buffer.addFirst(Double.toString(result));
		} catch(ArithmeticException e) {
			clearBuffer();
			System.out.println(e + "Cannot Divide by zero");
		}
	}

	/**
	 * Carries out addition using the provided first and second summands.
	 *
	 * @param  firstSummand   the first summand that is added to the second summand.
	 * @param  secondSummand  the second summand that is added to the first summand.
	 */
	private void add(final Double firstSummand, final Double secondSummand) {
		buffer.addFirst(Double.toString(firstSummand + secondSummand));
	}

	/**
	 * Carries out subtraction using provided minuend and subtrahend.
	 *
	 * @param  minuend     the number that is subtracted by the subtrahend.
	 * @param  subtrahend  the number that is subtracted from another.
	 */
	private void subtract(final Double minuend, final Double subtrahend) {
		buffer.addFirst(Double.toString(minuend - subtrahend));
	}

	/**
	 * Removes element that was added last.
	 */
	public void removeLastElement() {
		buffer.removeFirst();
	}

	/**
	 * Removes all elements from the buffer.
	 */
	public void clearBuffer() {
		buffer.clear();
	}

	/**
	 * Returns the size of the buffer.
	 */
	public int getBufferSize() {
		return buffer.size();
	}
}

