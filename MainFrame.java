/*H*****************************************************************************
 * Filename: MainFrame.java
 * Description: 
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame  {

	private ButtonPanel buttonPanel;
	private InputPrompt inputPrompt;

	public MainFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(350, 500);
		setLocation(500, 500);
		setTitle("Calculator 3");
		setLayout(new BorderLayout());
		buttonPanel = new ButtonPanel();
		inputPrompt = new InputPrompt();
		inputPrompt = buttonPanel.getInputPrompt();
		add(inputPrompt, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
	}
}
