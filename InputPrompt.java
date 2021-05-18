/*H*****************************************************************************
 * Filename: InputPrompt.java
 * Description: 
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputPrompt extends JPanel {

	private JTextArea textArea;
	private ArrayList<String> numberList;

	public InputPrompt() {
		textArea = new JTextArea(1, 8);
		textArea.setFont(new Font("Gill Sans", Font.PLAIN, 50));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		add(textArea);
		numberList = new ArrayList<>();
		setBackground(Color.BLACK);
	}

	public JTextArea getTextField() {
		return textArea;
	}

	public void appendNumber(String text) {
		textArea.append(text);
		numberList.add(text);
	}

	public ArrayList<String> getList() {
		return numberList;
	}

	public void clearText() {
		textArea.setText(null);
	}
}

