/*H*****************************************************************************
 * Filename: ButtonPanel.java
 * Description: 
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * 	     2021-05-11 Updated calculator logic.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

public class ButtonPanel extends JPanel {

	private InputPrompt inputPrompt;

	private Calculator calculator;

	private LinkedHashMap<String, JButton> buttons;

	public ButtonPanel() {
		GridLayout layout = new GridLayout(4,4);
		setLayout(layout);
		layout.setHgap(3);
		layout.setVgap(3);

		calculator = new Calculator();
		inputPrompt = new InputPrompt();
		buttons = new LinkedHashMap<>();

		initializeButtons();
		addButtons();
		buttons.get("+").setBackground(Color.RED);
		buttons.get("-").setBackground(Color.RED);
		buttons.get("*").setBackground(Color.RED);
		buttons.get("/").setBackground(Color.RED);
		buttons.get(".").setBackground(Color.RED);
		buttons.get("=").setFont(new Font("Arial", Font.BOLD, 15));
		buttons.get("=").setBackground(Color.ORANGE);
		buttons.get("=").setForeground(Color.WHITE);
		buttons.get("=").setOpaque(true);
		buttons.get("=").setBorderPainted(false);
		buttons.get("=").addActionListener(event -> {
			System.out.println(inputPrompt.getList());
			inputPrompt.clearText();
			inputPrompt.appendNumber(calculator.getResult() + "");
		});
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
	}

	private void initializeButtons() {
		buttons.put("7", makeButton("7"));
		buttons.put("8", makeButton("8"));
		buttons.put("9", makeButton("9"));
		buttons.put("+", makeButton("+"));
		buttons.put("4", makeButton("4"));
		buttons.put("5", makeButton("5"));
		buttons.put("6", makeButton("6"));
		buttons.put("-", makeButton("-"));
		buttons.put("1", makeButton("1"));
		buttons.put("2", makeButton("2"));
		buttons.put("3", makeButton("3"));
		buttons.put("*", makeButton("x"));
		buttons.put(".", makeButton("."));
		buttons.put("0", makeButton("0"));
		buttons.put("=", makeButton("="));
		buttons.put("/", makeButton("/"));
	}

	private void addButtons() {
		buttons.values().forEach(button -> {
			add(button);
		});
	}

	private JButton makeButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("Gill Sans", Font.BOLD, 20));
		button.setBackground(Color.darkGray);
		button.setForeground(Color.WHITE);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.addActionListener(event -> {
			String element = ((JButton) event.getSource()).getText();
			calculator.addElement(element);
			inputPrompt.appendNumber(element);
		});
		return button;
	}

	public InputPrompt getInputPrompt() {
		return inputPrompt;
	}

	protected void paintComponent(Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.CYAN);
	}
}
