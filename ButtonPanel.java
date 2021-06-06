/*H*****************************************************************************
 * Filename: ButtonPanel.java
 * Description: Panel that contains the buttons for the calculator. Formats
 * 		and styles all buttons.
 * Modified: 2021-05-05	Added header comments.
 * 	     2021-05-11 Updated calculator logic.
 * 	     2021-05-19 Refactored and updated GUI.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

/**
 * Initializes, formats, and styles all buttons for the calculator GUI. Requires
 * a calculator api for functionality.
 */
public class ButtonPanel extends JPanel {

	/** 
	 * The number of buttons in each row.
	 */
	private static final int ROWS = 5;

	/** 
	 * The number of buttons in each column. 
	 */
	private static final int COLS = 4;

	/** 
	 * The gap between the border of one button and another one.
	 */
	private static final int GAP = 3;

	/** 
	 * The display panel that displays input and results.
	 */
	private final DisplayPanel displayPanel;
	
	/** 
	 * The calculator object that provides functionality to the GUI.
	 */
	private final Calculator calculator;

	/**
	 * A mapping between symbols and their respective buttons.
	 */
	private final LinkedHashMap<Symbol, JButton> symbolButtons;

	/**
	 * A mapping between operators and their respective buttons.
	 */
	private final LinkedHashMap<Operator, JButton> operatorButtons;

	/**
	 * A mapping between number and their respective buttons.
	 */
	private final LinkedHashMap<Number, JButton> numberButtons;

	/**
	 * Constructs button panel. Initializes layout, displayPanel, calculator object, 
	 * and buttons. Formats layout, buttons, and adds buttons to layout.
	 */
	public ButtonPanel() {
		GridLayout layout = new GridLayout(ROWS, COLS);
		displayPanel = new DisplayPanel();
		calculator = new Calculator();
		symbolButtons = new LinkedHashMap<>();
		operatorButtons = new LinkedHashMap<>();
		numberButtons = new LinkedHashMap<>();
		formatGridLayout(layout);
		initializeButtons();
		styleOperatorAndSymbolButtons();
	}

	/**
	 * Set the layout for this panel. Set the horizontal and vertical gap
	 * between buttons.
	 */
	private void formatGridLayout(final GridLayout layout) {
		setLayout(layout);
		layout.setHgap(GAP);
		layout.setVgap(GAP);
	}

	/**
	 * Initialize all buttons and formats equal, clear, and delete buttons.
	 * The appropriate calculator symbol is mapped to the corresponding button.
	 */
	private void initializeButtons() {
		symbolButtons.put(Symbol.CLEAR, makeButton("clr"));
		symbolButtons.put(Symbol.DELETE, makeButton("del"));
		operatorButtons.put(Operator.EXPONENTIATION, makeButton("^"));
		operatorButtons.put(Operator.MULTIPLICATION, makeButton("x"));

		numberButtons.put(Number.SEVEN, makeButton("7"));
		numberButtons.put(Number.EIGHT, makeButton("8"));
		numberButtons.put(Number.NINE, makeButton("9"));
		operatorButtons.put(Operator.DIVISION, makeButton("/"));

		numberButtons.put(Number.FOUR, makeButton("4"));
		numberButtons.put(Number.FIVE, makeButton("5"));
		numberButtons.put(Number.SIX, makeButton("6"));
		operatorButtons.put(Operator.ADDITION, makeButton("+"));

		numberButtons.put(Number.ONE, makeButton("1"));
		numberButtons.put(Number.TWO, makeButton("2"));
		numberButtons.put(Number.THREE, makeButton("3"));
		operatorButtons.put(Operator.SUBTRACTION, makeButton("-"));

		symbolButtons.put(Symbol.DECIMAL, makeButton("."));
		numberButtons.put(Number.ZERO, makeButton("0"));
		symbolButtons.put(Symbol.NEGATIVE, makeButton("-"));
		symbolButtons.put(Symbol.EQUAL, makeButton("="));

		formatEqualButton(displayPanel);
		formatClearButton(displayPanel);
		formatDeleteButton(displayPanel);
	}

	/**
	 * Creates, formats, adds functionality  and returns a new JButton with
	 * the provided name. General functionality added to all buttons.
	 *
	 * @param  name  the string displayed on the button.
	 * @return a new formatted button.
	 */
	private JButton makeButton(final String name) {
		final JButton button = new JButton(name);
		button.setFont(new Font("Gill Sans", Font.BOLD, 20));
		button.setBackground(Color.darkGray);
		button.setForeground(Color.WHITE);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.addActionListener(event -> {
			final String element = ((JButton) event.getSource()).getText();
			assert element != null : "element is null";
			numberButtons.forEach((k, v) -> {
				if (v.getText().equals(element)) {
						calculator.addElement(k.getNumber());
						displayPanel.appendElement(v.getText());
					}
			});
			operatorButtons.forEach((k, v) -> {
				if (v.getText().equals(element)) {
					calculator.addElement(k.getOperator());
					displayPanel.appendElement(v.getText());
				}
			});
		});
		add(button);
		return button;
	}

	/**
	 * Adds functionality to the equal button. On action event, display panel 
	 * is cleared and result is appended to the display panel.
	 * 
	 * @param  displayPanel  the display panel that the equal action modifies.
	 */
	private void formatEqualButton(final DisplayPanel displayPanel) {
		symbolButtons.get(Symbol.EQUAL).addActionListener(event -> {
			if (calculator.getBufferSize() > 0) {
				displayPanel.clearText();
				displayPanel.appendElement(Double
						.toString(calculator.getResult()));
			}
		});
	}

	/**
	 * Adds functionality to the clear button. On action event the display panel, 
	 * and calculator buffer is cleared.
	 *
	 * @param  displayPanel  the display panel that the clear action modifies.  
	 */
	private void formatClearButton(final DisplayPanel displayPanel) {
		symbolButtons.get(Symbol.CLEAR).addActionListener(event -> {
			displayPanel.clearText();
			displayPanel.getList().clear();
			calculator.clearBuffer();
		});
	}

	/**
	 * Adds functionality to the delete button. On action event, the most 
	 * recent element appended to the display panel and added to the calculator
	 * buffer is removed.
	 */
	private void formatDeleteButton(final DisplayPanel displayPanel) {
		symbolButtons.get(Symbol.DELETE).addActionListener(event -> {
			if (calculator.getBufferSize() > 0) {
				calculator.removeLastElement();
				displayPanel.removeLastElement();
			}
		});
	}

	/**
	 * The operator buttons are styled.
	 */
	private void styleOperatorAndSymbolButtons() {
		operatorButtons.values().stream().forEach(button -> 
					button.setBackground(Color.RED));
		symbolButtons.values().stream().forEach(button -> 
					button.setBackground(Color.RED));
		styleEqualButton();
	}

	/**
	 * Styles the equal button in a different font and color than the rest of 
	 * the operator buttons.
	 */
	private void styleEqualButton() {
		symbolButtons.get(Symbol.EQUAL).setFont(
				new Font("Arial", Font.BOLD, 15));
		symbolButtons.get(Symbol.EQUAL).setBackground(Color.ORANGE);
		symbolButtons.get(Symbol.EQUAL).setForeground(Color.WHITE);
		symbolButtons.get(Symbol.EQUAL).setOpaque(true);
		symbolButtons.get(Symbol.EQUAL).setBorderPainted(false);
	}

	/**
	 * Returns the display panel object.
	 * @return the display panel.
	 */
	public DisplayPanel getDisplayPanel() {
		return displayPanel;
	}

	protected void paintComponent(Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.CYAN);
	}
}

