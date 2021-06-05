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
	 * A mapping between a Calculator Symbol and a button.
	 */
	private final LinkedHashMap<Calculator.Symbol, JButton> buttons;

	/**
	 * Constructs button panel. Initializes layout, displayPanel, calculator object, 
	 * and buttons. Formats layout, buttons, and adds buttons to layout.
	 */
	public ButtonPanel() {
		GridLayout layout = new GridLayout(ROWS, COLS);
		displayPanel = new DisplayPanel();
		calculator = new Calculator();
		buttons = new LinkedHashMap<>();
		formatGridLayout(layout);
		initializeButtons();
		styleOperatorButtons();
		addButtons();
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
		buttons.put(Calculator.Symbol.CLEAR, makeButton("clr"));
		buttons.put(Calculator.Symbol.DELETE, makeButton("del"));
		buttons.put(Calculator.Symbol.EXPONENTIATION, makeButton("^"));
		buttons.put(Calculator.Symbol.MULTIPLICATION, makeButton("x"));

		buttons.put(Calculator.Symbol.SEVEN, makeButton("7"));
		buttons.put(Calculator.Symbol.EIGHT, makeButton("8"));
		buttons.put(Calculator.Symbol.NINE, makeButton("9"));
		buttons.put(Calculator.Symbol.DIVISION, makeButton("/"));

		buttons.put(Calculator.Symbol.FOUR, makeButton("4"));
		buttons.put(Calculator.Symbol.FIVE, makeButton("5"));
		buttons.put(Calculator.Symbol.SIX, makeButton("6"));
		buttons.put(Calculator.Symbol.ADDITION, makeButton("+"));

		buttons.put(Calculator.Symbol.ONE, makeButton("1"));
		buttons.put(Calculator.Symbol.TWO, makeButton("2"));
		buttons.put(Calculator.Symbol.THREE, makeButton("3"));
		buttons.put(Calculator.Symbol.SUBTRACTION, makeButton("-"));

		buttons.put(Calculator.Symbol.DECIMAL, makeButton("."));
		buttons.put(Calculator.Symbol.ZERO, makeButton("0"));
		buttons.put(Calculator.Symbol.NEGATIVE, makeButton("-"));
		buttons.put(Calculator.Symbol.EQUAL, makeButton("="));

		formatEqualButton(displayPanel);
		formatClearButton(displayPanel);
		formatDeleteButton(displayPanel);
	}

	/**
	 * Creates, formats, adds funcionality  and returns a new JButton with 
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
			buttons.forEach((k, v) -> {
				if (v.getText().equals(element)
						&& !k.equals(Calculator.Symbol.CLEAR)
						&& !k.equals(Calculator.Symbol.DELETE)
						&& !k.equals(Calculator.Symbol.NEGATIVE)) {

						calculator.addElement(calculator.symbolMap.get(k));
					}
			});
			if (!element.equals(buttons.get(Calculator.Symbol.EQUAL).getText())
			    && !element.equals(buttons.get(Calculator.Symbol.CLEAR).getText())
			    && !element.equals(buttons.get(Calculator.Symbol.DELETE).getText())) {
				displayPanel.appendElement(element);
			}
		});
		return button;
	}

	/**
	 * Adds functionality to the equal button. On action event, display panel 
	 * is cleared and result is appended to the display panel.
	 * 
	 * @param  displayPanel  the display panel that the equal action modifies.
	 */
	private void formatEqualButton(final DisplayPanel displayPanel) {
		buttons.get(Calculator.Symbol.EQUAL).addActionListener(event -> {
			if (calculator.getBufferSize() > 0) {
				displayPanel.clearText();
				displayPanel.appendElement(Double.toString(calculator.getResult()));
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
		buttons.get(Calculator.Symbol.CLEAR).addActionListener(event -> {
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
		buttons.get(Calculator.Symbol.DELETE).addActionListener(event -> {
			if (calculator.getBufferSize() > 0) {
				calculator.removeLastElement();
				displayPanel.removeLastElement();
			}
		});
	}

	/**
	 * The operator buttons are styled.
	 */
	private void styleOperatorButtons() {
		buttons.get(Calculator.Symbol.ADDITION).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.SUBTRACTION).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.MULTIPLICATION).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.DIVISION).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.EXPONENTIATION).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.DECIMAL).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.CLEAR).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.DELETE).setBackground(Color.RED);
		buttons.get(Calculator.Symbol.NEGATIVE).setBackground(Color.RED);
		styleEqualButton();
	}

	/**
	 * Styles the equal button in a different font and color than the rest of 
	 * the operator buttons.
	 */
	private void styleEqualButton() {
		buttons.get(Calculator.Symbol.EQUAL).setFont(new Font("Arial", Font.BOLD, 15));
		buttons.get(Calculator.Symbol.EQUAL).setBackground(Color.ORANGE);
		buttons.get(Calculator.Symbol.EQUAL).setForeground(Color.WHITE);
		buttons.get(Calculator.Symbol.EQUAL).setOpaque(true);
		buttons.get(Calculator.Symbol.EQUAL).setBorderPainted(false);
	}

	/**
	 * Adds the buttons to this panel.
	 */
	private void addButtons() {
		buttons.values().forEach(this::add);
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

