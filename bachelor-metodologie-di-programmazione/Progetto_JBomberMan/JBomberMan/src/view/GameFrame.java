package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Class to build the frame to draw on the screen
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class GameFrame extends JFrame {
	
	/**
	 * Class constructor 
	 * @param title			the title of the frame 
	 * @param gamePanel		the main panel to draw inside the frame 
	 */
	public GameFrame(String title, GamePanel gamePanel) {	
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		this.add(gamePanel);
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}