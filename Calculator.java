/*H*****************************************************************************
 * Filename: Calculator.java
 * Description: Defines a calculator class. Calculator implemented using a
 * 		stack.
 * Comment: Paranthesis implementation is not complete.
 * Modified: 2021-05-11	File Created.
 * 	     2021-05-17 Finished implementation w/o parenthesis.
 * 	     2021-06-01 Refactored and using a map of ENUMs for operators
 * 	     		and digits.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * Defines a calculator that can perform the following calculations: 
 * exponentiation, multiplication, division, addition, and subtraction. 
 */
public class Calculator {

	private final Deque<String> buffer;
	private String recentOperator;

	/**
	 * Symbols that calculator can handle.
	 */
	protected enum Symbol {
		EXPONENTIATION,
		MULTIPLICATION,
		DIVISION,
		ADDITION,
		SUBTRACTION,
		CLEAR,
		DELETE,
		DECIMAL,
		NEGATIVE,
		EQUAL,
		ZERO,
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE
	}	

	/**
	 * Maps symbols to their string counter-part.
	 */
	protected final EnumMap<Symbol, String> symbolMap = new EnumMap<>(Symbol.class);

	/**
	 * Constructs calculator object. Initializes buffer and sets the official
	 * list of acceptable operators.
	 */
	public Calculator() {
		buffer = new ArrayDeque<>();
		recentOperator = null;
		buildSymbolMap();
	}

	private void buildSymbolMap() {
		symbolMap.put(Symbol.EXPONENTIATION, "^");
		symbolMap.put(Symbol.MULTIPLICATION, "*");
		symbolMap.put(Symbol.DIVISION, "/");
		symbolMap.put(Symbol.ADDITION, "+");
		symbolMap.put(Symbol.SUBTRACTION, "-");
		symbolMap.put(Symbol.DECIMAL, ".");
		symbolMap.put(Symbol.EQUAL, "=");
		symbolMap.put(Symbol.ZERO, "0");
		symbolMap.put(Symbol.ONE, "1");
		symbolMap.put(Symbol.TWO, "2");
		symbolMap.put(Symbol.THREE, "3");
		symbolMap.put(Symbol.FOUR, "4");
		symbolMap.put(Symbol.FIVE, "5");
		symbolMap.put(Symbol.SIX, "6");
		symbolMap.put(Symbol.SEVEN, "7");
		symbolMap.put(Symbol.EIGHT, "8");
		symbolMap.put(Symbol.NINE, "9");
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

	private void checkIfMultiDigit() {
		if (buffer.size() > 1) {
			String currentElement = buffer.removeFirst();
			while (!isOperator(currentElement) && buffer.size() > 0) {
				String nextElement = buffer.removeFirst();
				if (isOperator(nextElement)) {
					buffer.addFirst(nextElement);
					break;
				} else {
					currentElement = combineDigits(nextElement, currentElement);
				}
			}
			buffer.addFirst(currentElement);
		}
	}

	private String combineDigits(String firstDigit, final String secondDigit) {
		firstDigit += secondDigit;
		return firstDigit;
	}

	private boolean isOperator(final String element) {
		final String[] operators = {
			symbolMap.get(Symbol.EXPONENTIATION),
			symbolMap.get(Symbol.MULTIPLICATION),
			symbolMap.get(Symbol.DIVISION),
			symbolMap.get(Symbol.ADDITION),
			symbolMap.get(Symbol.SUBTRACTION)
		};
		for (String operator : operators) {
			if (element.equals(operator)) {
				recentOperator = element;
				return true;
			}
		}
		return false;
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

	public void calculate() {
		exponentiateRemainingElements();
		multiplyAndDivideRemainingElements();
		addAndSubtractRemainingElements(); 
	}

	private void exponentiateRemainingElements() {
		operateOnElements(
				this::isExponentiation, 
				this::isExponentiation, 
				this::exponentiate,
				this::exponentiate
				);
	}

	private void multiplyAndDivideRemainingElements() {
		operateOnElements(
				this::isMultiplication, 
				this::isDivision, 
				this::multiply,
				this::divide
				);
	}

	private void addAndSubtractRemainingElements() {
		operateOnElements(
				this::isAddition, 
				this::isSubtraction, 
				this::add,
				this::subtract
				);
	}

	private void operateOnElements(
					Predicate<String> isPrimaryFunction,
					Predicate<String> isSecondaryFunction,
					BiConsumer<Double, Double> primaryFunction,
					BiConsumer<Double, Double> secondaryFunction
					) {
		final Deque<String> temp = new ArrayDeque<>();
		while (buffer.size() > 0) {
			final String element = buffer.removeFirst();
			if (isOperator(element)) {
				if (isPrimaryFunction.test(element)) {
					final Double firstOperand = Double.parseDouble(buffer.removeFirst());
					final Double secondOperand = Double.parseDouble(temp.removeFirst());
					primaryFunction.accept(firstOperand, secondOperand);
				} else if (isSecondaryFunction.test(element)) {
					final Double firstOperand = Double.parseDouble(buffer.removeFirst());
					final Double secondOperand = Double.parseDouble(temp.removeFirst());
					secondaryFunction.accept(firstOperand, secondOperand);
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

	public boolean isExponentiation(final String element) {
		return element.equals(symbolMap.get(Symbol.EXPONENTIATION));
	}

	private boolean isMultiplication(final String element) {
		return element.equals(symbolMap.get(Symbol.MULTIPLICATION));
	}

	private boolean isDivision(final String element) {
		return element.equals(symbolMap.get(Symbol.DIVISION));
	}

	private boolean isAddition(final String element) {
		return element.equals(symbolMap.get(Symbol.ADDITION));
	}

	private boolean isSubtraction(final String element) {
		return element.equals(symbolMap.get(Symbol.SUBTRACTION));
	}

	public void exponentiate(final Double base, final Double exponent) {
		final double result = Math.pow(base.doubleValue(), exponent.doubleValue());
		buffer.addFirst(Double.toString(result));
	}

	private void multiply(final Double multiplier, final Double multiplicand) {
		final double result = multiplier.doubleValue() * multiplicand.doubleValue();
		buffer.addFirst(Double.toString(result));
	}

	private void divide(final Double dividend, final Double divisor) {
		double result;
		try {
			result = dividend.doubleValue() / divisor.doubleValue();
			buffer.addFirst(Double.toString(result));
		} catch(ArithmeticException e) {
			clearBuffer();
			System.out.println(e + "Cannot Divide by zero");
		}
	}

	private void add(final Double firstSummand, final Double secondSummand) {
		final double result = firstSummand.doubleValue() + secondSummand.doubleValue();
		buffer.addFirst(Double.toString(result));
	}

	private void subtract(final Double minuend, final Double subtrahend) {
		final double result = minuend.doubleValue() - subtrahend.doubleValue();
		buffer.addFirst(Double.toString(result));
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

