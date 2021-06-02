/*H*****************************************************************************
 * Filename: DisplayPanel.java
 * Description: 
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Displays the input text from the user and the calculated
 * result.
 */
public class DisplayPanel extends JPanel {

	private final JTextArea textArea;
	private final ArrayList<String> numberList;

	public DisplayPanel() {
		textArea = new JTextArea(1, 8);
		textArea.setFont(new Font("Gill Sans", Font.PLAIN, 50));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		add(textArea);
		numberList = new ArrayList<>();
		setBackground(Color.BLACK);
	}

	public void appendElement(String text) {
		textArea.append(text);
		numberList.add(text);
	}

	public void removeLastElement() {
		if (numberList.size() > 0) {
			numberList.remove(numberList.size()- 1);
			clearText();
			for (String element : numberList) {
				textArea.append(element);
			}
		}
	}

	public ArrayList<String> getList() {
		return numberList;
	}

	public void clearText() {
		textArea.setText(null);
	}
}

