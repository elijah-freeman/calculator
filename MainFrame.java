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

	private static final int CALCULATOR_WIDTH = 350;
	private static final int CALCULATOR_HEIGHT = 500;

	public MainFrame() {
		setTitle("Calculator");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(CALCULATOR_WIDTH, CALCULATOR_HEIGHT);
		setResizable(false);
		setPositionOnScreen();
		setupComponents();
	}

	private void setPositionOnScreen() {
		final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		final int xCoordinate = screenWidth/2 - CALCULATOR_WIDTH/2;
		final int yCoordinate = screenHeight/2 - CALCULATOR_HEIGHT/2;
		setLocation(xCoordinate, yCoordinate);
	}

	private void setupComponents() {
		setLayout(new BorderLayout());
		ButtonPanel buttonPanel = new ButtonPanel();
		DisplayPanel inputPrompt = new DisplayPanel();
		inputPrompt = buttonPanel.getDisplayPanel();
		add(inputPrompt, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
	}
}
