/*H*****************************************************************************
 * Filename: MainFrame.java
 * Description: Responsible for constructing and positioning all calculator GUI
 * 		components.
 * Modified: 2021-05-05	Added header comments.
 *	     2021-06-05	Refactored and added javadoc.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import javax.swing.*;
import java.awt.*;

/**
 * Constructs and formats the calculator GUI.
 */
public class MainFrame extends JFrame  {

	/**
	 * The width of the calculator GUI.
	 */
	private static final int CALCULATOR_WIDTH = 350;

	/**
	 * The height of the calculator GUI.
	 */
	private static final int CALCULATOR_HEIGHT = 500;

	/**
	 * Initializes and formats all calculator components.
	 */
	public MainFrame() {
		setTitle("Calculator");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(CALCULATOR_WIDTH, CALCULATOR_HEIGHT);
		setResizable(false);
		setPositionOnScreen();
		setupComponents();
	}

	/**
	 * Sets up the initial position of the GUI on the screen.
	 */
	private void setPositionOnScreen() {
		final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		final int xCoordinate = screenWidth/2 - CALCULATOR_WIDTH/2;
		final int yCoordinate = screenHeight/2 - CALCULATOR_HEIGHT/2;
		setLocation(xCoordinate, yCoordinate);
	}

	/**
	 * Sets up and positions on Calculator GUI components.
	 */
	private void setupComponents() {
		setLayout(new BorderLayout());
		ButtonPanel buttonPanel = new ButtonPanel();
		DisplayPanel inputPrompt = buttonPanel.getDisplayPanel();
		add(inputPrompt, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
	}
}
