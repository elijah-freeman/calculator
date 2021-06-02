/*H*****************************************************************************
 * Filename: ButtonPanel.java
 * Description: Panel that contains the buttons for the calculator. Formats
 * 		and styles all buttons.
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * 	     2021-05-11 Updated calculator logic.
 * 	     2021-05-19 Refactored and updated GUI.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

/**
 * Initializes, formats, and styles all buttons for the calculator GUI.
 */
public class ButtonPanel extends JPanel {

	private DisplayPanel displayPanel;
	private Calculator calculator;
	private LinkedHashMap<Calculator.Symbol, JButton> buttons;

	/**
	 * Constructs button panel. Initializes and formats all buttons.
	 */
	public ButtonPanel() {
		GridLayout layout = new GridLayout(5,4);
		displayPanel = new DisplayPanel();
		calculator = new Calculator();
		buttons = new LinkedHashMap<>();
		formatGridLayout(layout);
		initializeButtons();
		addButtons();
		styleOperatorButtons();
		formatEqualButton(displayPanel);
		formatClearButton(displayPanel);
		formatDeleteButton(displayPanel);
	}

	/**
	 * Format the layout for calculator GUI buttons.
	 */
	private void formatGridLayout(GridLayout layout) {
		setLayout(layout);
		layout.setHgap(3);
		layout.setVgap(3);
	}

	/**
	 * Initialize all calculator GUI buttons.
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
	}

	/**
	 * Add all buttons to this button panel.
	 */ 
	private void addButtons() {
		buttons.values().forEach(this::add);
	}

	/**
	 * Styles all operator buttons.
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
	}

	/**
	 * Button factory method that makes and returns a generic stylized button
	 * with an eventlistener attached.
	 *
	 * @param  name  a String name that is to be displayed on the button (e.g. "+")
	 * @return	 a stylized JButton with the name displayed. 
	 */
	private JButton makeButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("Gill Sans", Font.BOLD, 20));
		button.setBackground(Color.darkGray);
		button.setForeground(Color.WHITE);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.addActionListener(event -> {
			String element = ((JButton) event.getSource()).getText();
			buttons.forEach((k, v) -> {
				if (v.getText().equals(element)
						&& !k.equals(Calculator.Symbol.CLEAR)
						&& !k.equals(Calculator.Symbol.DELETE)
						&& !k.equals(Calculator.Symbol.NEGATIVE)) {

						calculator.addElement(calculator.symbolMap.get(k));
					}
			});
			assert(element != null);
			if (!element.equals(buttons.get(Calculator.Symbol.EQUAL).getText())
			    && !element.equals(buttons.get(Calculator.Symbol.CLEAR).getText())
			    && !element.equals(buttons.get(Calculator.Symbol.DELETE).getText())) {
				displayPanel.appendElement(element);
			}
		});
		return button;
	}

	/**
	 * Formats the equal button. Clears display panel and appends the 
	 * result to the display panel.
	 *
	 * @param  displayPanel  a panel that displays the input text.
	 */
	private void formatEqualButton(DisplayPanel displayPanel) {
		styleEqualButton();
		buttons.get(Calculator.Symbol.EQUAL).addActionListener(event -> {
			System.out.println(displayPanel.getList());
			if (calculator.getBufferSize() > 0) {
				displayPanel.clearText();
				displayPanel.appendElement(calculator.getResult() + "");
			}
		});
	}

	/**
	 * Formats the clear button. Clears the display panel and calculator buffer.
	 *
	 * @param  displayPanel  a panel that displays the input text.
	 */
	private void formatClearButton(DisplayPanel displayPanel) {
		buttons.get(Calculator.Symbol.CLEAR).addActionListener(event -> {
			displayPanel.clearText();
			displayPanel.getList().clear();
			calculator.clearBuffer();
		});
	}
	/**
	 * Formats the delete button. One input character deleted at a time from 
	 * the display panel.
	 *
	 * @param  displayPanel  a panel that displays the input text.
	 */
	private void formatDeleteButton(DisplayPanel displayPanel) {
		buttons.get(Calculator.Symbol.DELETE).addActionListener(event -> {
			if (calculator.getBufferSize() > 0) {
				calculator.removeLastElement();
				displayPanel.removeLastElement();
			}
		});
	}

	/**
	 * Styles the equal button.
	 */
	private void styleEqualButton() {
		buttons.get(Calculator.Symbol.EQUAL).setFont(new Font("Arial", Font.BOLD, 15));
		buttons.get(Calculator.Symbol.EQUAL).setBackground(Color.ORANGE);
		buttons.get(Calculator.Symbol.EQUAL).setForeground(Color.WHITE);
		buttons.get(Calculator.Symbol.EQUAL).setOpaque(true);
		buttons.get(Calculator.Symbol.EQUAL).setBorderPainted(false);
	}

	/**
	 * Getter method for displayPanel.
	 *
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

