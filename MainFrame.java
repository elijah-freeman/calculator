/*H*****************************************************************************
 * Filename: MainFrame.java
 * Description: 
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame  {

	public MainFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(350, 500);
		setLocation(500, 500);
		setTitle("Calculator");
		setLayout(new BorderLayout());
		ButtonPanel buttonPanel = new ButtonPanel();
		new DisplayPanel();
		DisplayPanel inputPrompt;
		inputPrompt = buttonPanel.getDisplayPanel();
		add(inputPrompt, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
	}
}
