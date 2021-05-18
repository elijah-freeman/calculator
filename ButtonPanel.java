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

public class ButtonPanel extends JPanel {

private static final int BUTTON_WIDTH = 80;
private static final int BUTTON_HEIGHT = 50;

private static final int X1 = 0;
private static final int X2 = BUTTON_WIDTH;
private static final int X3 = BUTTON_WIDTH * 2;
private static final int X4 = BUTTON_WIDTH * 3;

private static final int Y1 = 0;
private static final int Y2 = BUTTON_HEIGHT;
private static final int Y3 = BUTTON_HEIGHT * 2;
private static final int Y4 = BUTTON_HEIGHT * 3;

private static final int ADJUST_1 = 5;
private static final int ADJUST_2 = ADJUST_1 * 2;
private static final int ADJUST_3 = ADJUST_1 * 3;

private static final int ADJUSTY_1 = 20;
private static final int ADJUSTY_2 = ADJUSTY_1 * 2;
private static final int ADJUSTY_3 = ADJUSTY_1 * 3;

private InputPrompt inputPrompt;

public ButtonPanel() {
	GridLayout layout = new GridLayout(4,4);
	setLayout(layout);
	layout.setHgap(3);
	layout.setVgap(3);

	inputPrompt = new InputPrompt();

	JButton button7 = makeButton("7");
	button7.setBounds(X1, Y1, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button7);

	JButton button8 = makeButton("8");
	button8.setBounds(X2 + ADJUST_1, Y1, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button8);

	JButton button9 = makeButton("9");
	button9.setBounds(X3+ ADJUST_2, Y1, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button9);

	JButton addButton = makeButton("+");
	addButton.setBounds(X4+ ADJUST_3, Y1, BUTTON_WIDTH, BUTTON_HEIGHT);
	addButton.setBackground(Color.RED);
	add(addButton);

	JButton button4 = makeButton("4");
	button4.setBounds(X1, Y2 + ADJUSTY_1, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button4);

	JButton button5 = makeButton("5");
	button5.setBounds(X2+ ADJUST_1, Y2+ ADJUSTY_1, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button5);

	JButton button6 = makeButton("6");
	button6.setBounds(X3+ ADJUST_2, Y2+ ADJUSTY_1, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button6);

	JButton minustButton = makeButton("-");
	minustButton.setBounds(X4+ ADJUST_3, Y2+ ADJUSTY_1, BUTTON_WIDTH, BUTTON_HEIGHT);
	minustButton.setBackground(Color.RED);
	add(minustButton);

	JButton button1 = makeButton("1");
	button1.setBounds(X1, Y3+ ADJUSTY_2, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button1);

	JButton button2 = makeButton("2");
	button2.setBounds(X2+ ADJUST_1, Y3+ ADJUSTY_2, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button2);

	JButton button3 = makeButton("3");
	button3.setBounds(X3+ ADJUST_2, Y3+ ADJUSTY_2, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button3);

	JButton multiplyButton = makeButton("X");
	multiplyButton.setBounds(X4+ ADJUST_3, Y3+ ADJUSTY_2, BUTTON_WIDTH, BUTTON_HEIGHT);
	multiplyButton.setBackground(Color.RED);
	add(multiplyButton);

	JButton decimalButton = makeButton(".");
	decimalButton.setBounds(X1, Y4+ ADJUSTY_3, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(decimalButton);

	JButton button0 = makeButton("0");
	button0.setBounds(X2+ ADJUST_1, Y4+ ADJUSTY_3, BUTTON_WIDTH, BUTTON_HEIGHT);
	add(button0);

	JButton equalButton = new JButton("=");
	equalButton.setBounds(X3+ ADJUST_2, Y4+ ADJUSTY_3, BUTTON_WIDTH, BUTTON_HEIGHT);
	equalButton.setFont(new Font("Arial", Font.BOLD, 15));
	equalButton.setBackground(Color.ORANGE);
	equalButton.setForeground(Color.WHITE);
	equalButton.setOpaque(true);
	equalButton.setBorderPainted(false);
	add(equalButton);

	equalButton.addActionListener(event -> {
		System.out.println(inputPrompt.getList());
		inputPrompt.clearText();

		ArrayList<String> numberList = inputPrompt.getList();

		if (numberList.contains("+") || numberList.contains("-") || numberList.contains("/") || numberList.contains("X")) {

		    int acceptable = 0;
		    for (String x : numberList) {
			if (x.equals("+")) {
			acceptable++;
			} else if (x.equals("-")) {
			acceptable++;
			} else if (x.equals("/")) {
			acceptable++;
			} else if (x.equals("X")) {
			acceptable++;
			}
		    }

		    if (acceptable == 1) {
			if (numberList.contains("+")) {
			int addIndex = numberList.indexOf("+");
			List subList = numberList.subList(0, addIndex);
			List subList2 = numberList.subList(addIndex + 1, numberList.size());

			String num = "";
			String num2 = "";

			for (Object x : subList) {
			    num = num + (String) x;
			}

			for (Object x : subList2) {
			    num2 = num2 + (String) x;
			}

			int numb1 = Integer.parseInt(num);
			int numb2 = Integer.parseInt(num2);
			int result = numb1 + numb2;

			inputPrompt.appendNumber(result + "");

			numberList.clear();
			numberList.add(result + "");
			} else if (numberList.contains("-")) {
			int addIndex = numberList.indexOf("-");
			List subList = numberList.subList(0, addIndex);
			List subList2 = numberList.subList(addIndex + 1, numberList.size());

			String num = "";
			String num2 = "";

			for (Object x : subList) {
			    num = num + (String) x;
			}

			for (Object x : subList2) {
			    num2 = num2 + (String) x;
			}

			int numb1 = Integer.parseInt(num);
			int numb2 = Integer.parseInt(num2);
			int result = numb1 - numb2;

			inputPrompt.appendNumber(result + "");

			numberList.clear();
			numberList.add(result + "");
			} else if (numberList.contains("/")) {
			int addIndex = numberList.indexOf("/");
			List subList = numberList.subList(0, addIndex);
			List subList2 = numberList.subList(addIndex + 1, numberList.size());

			String num = "";
			String num2 = "";

			for (Object x : subList) {
			    num = num + (String) x;
			}

			for (Object x : subList2) {
			    num2 = num2 + (String) x;
			}

			int numb1 = Integer.parseInt(num);
			int numb2 = Integer.parseInt(num2);
			int result = 0;
			if (numb2 != 0) {
			     result = numb1 / numb2;
			} else {

			}

			inputPrompt.appendNumber(result + "");

			numberList.clear();
			numberList.add(result + "");
			} else if (numberList.contains("X")) {
			int addIndex = numberList.indexOf("X");
			List subList = numberList.subList(0, addIndex);
			List subList2 = numberList.subList(addIndex + 1, numberList.size());

			String num = "";
			String num2 = "";

			for (Object x : subList) {
			    num = num + (String) x;
			}

			for (Object x : subList2) {
			    num2 = num2 + (String) x;
			}

			int numb1 = Integer.parseInt(num);
			int numb2 = Integer.parseInt(num2);
			int result = numb1 * numb2;

			inputPrompt.appendNumber(result + "");

			numberList.clear();
			numberList.add(result + "");
			}
			acceptable = 0;
		    } else {
			numberList.clear();
		    }
	}
});

JButton divideButton = makeButton("/");
divideButton.setBounds(X4 + ADJUST_3, Y4 + ADJUSTY_3, BUTTON_WIDTH, BUTTON_HEIGHT);
add(divideButton);

//setVisible(true);
//setBackground(Color.BLACK);
this.setOpaque(true);
this.setBackground(Color.BLACK);
}

public JButton makeButton(String name) {
    JButton button = new JButton(name);

    button.setFont(new Font("Gill Sans", Font.BOLD, 20));
    button.setBackground(Color.darkGray);
    button.setForeground(Color.WHITE);
    button.setOpaque(true);
    button.setBorderPainted(false);

    button.addActionListener(event -> {
        String number = ((JButton) event.getSource()).getText();
        inputPrompt.appendNumber(number);
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
