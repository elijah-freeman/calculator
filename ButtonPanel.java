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
	private LinkedHashMap<String, JButton> buttons;

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
		buttons.put("clr", makeButton("clr"));
		buttons.put("del", makeButton("del"));
		buttons.put("^", makeButton("^"));
		buttons.put("*", makeButton("x"));

		buttons.put("7", makeButton("7"));
		buttons.put("8", makeButton("8"));
		buttons.put("9", makeButton("9"));
		buttons.put("/", makeButton("/"));

		buttons.put("4", makeButton("4"));
		buttons.put("5", makeButton("5"));
		buttons.put("6", makeButton("6"));
		buttons.put("+", makeButton("+"));

		buttons.put("1", makeButton("1"));
		buttons.put("2", makeButton("2"));
		buttons.put("3", makeButton("3"));
		buttons.put("-", makeButton("-"));

		buttons.put(".", makeButton("."));
		buttons.put("0", makeButton("0"));
		buttons.put("neg", makeButton("-"));
		buttons.put("=", makeButton("="));
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
		buttons.get("+").setBackground(Color.RED);
		buttons.get("-").setBackground(Color.RED);
		buttons.get("*").setBackground(Color.RED);
		buttons.get("/").setBackground(Color.RED);
		buttons.get(".").setBackground(Color.RED);
		buttons.get("^").setBackground(Color.RED);
		buttons.get("clr").setBackground(Color.RED);
		buttons.get("del").setBackground(Color.RED);
		buttons.get("neg").setBackground(Color.RED);
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
						&& !k.equals("clr")
						&& !k.equals("del")
						&& !k.equals("neg")) {
					calculator.addElement(k);
				}
			});
			assert(element != null);
			if (!element.equals("=") 
			    && !element.equals("clr") 
			    && !element.equals("del")) {
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
		buttons.get("=").addActionListener(event -> {
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
		buttons.get("clr").addActionListener(event -> {
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
		buttons.get("del").addActionListener(event -> {
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
		buttons.get("=").setFont(new Font("Arial", Font.BOLD, 15));
		buttons.get("=").setBackground(Color.ORANGE);
		buttons.get("=").setForeground(Color.WHITE);
		buttons.get("=").setOpaque(true);
		buttons.get("=").setBorderPainted(false);
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

