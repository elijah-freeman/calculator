/*H*****************************************************************************
 * Filename: DisplayPanel.java
 * Description: Panel where the input and output is displayed to the user.
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

	/**
	 * The text area where the input is displayed.
	 */
	private final JTextArea textArea;

	/**
	 * A list of elements that are currently displayed to 
	 * the text area.
	 */
	private final ArrayList<String> numberList;

	/**
	 * Construct the text area, formats, and adds to this panel.
	 */
	public DisplayPanel() {
		textArea = new JTextArea(1, 8);
		textArea.setFont(new Font("Gill Sans", Font.PLAIN, 50));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		add(textArea);
		numberList = new ArrayList<>();
		setBackground(Color.BLACK);
	}

	/**
	 * Appends an element to the text area.
	 */
	public void appendElement(String text) {
		textArea.append(text);
		numberList.add(text);
	}

	/**
	 * Removes the last element to the text area.
	 */
	public void removeLastElement() {
		if (numberList.size() > 0) {
			numberList.remove(numberList.size()- 1);
			clearText();
			for (String element : numberList) {
				textArea.append(element);
			}
		}
	}

	/**
	 * Returns the list of elements that are currently displayed
	 * on the text area.
	 */
	public ArrayList<String> getList() {
		return numberList;
	}

	/**
	 * Removes all elements from the text area.
	 */
	public void clearText() {
		textArea.setText(null);
	}
}

