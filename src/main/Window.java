package main;

import java.awt.*;

import javax.swing.JFrame;

/**
 * This class is only used for making a main frame for the game. Object of this
 * class is made in the constructor of Game class.
 * 
 * @author Kimhuibeom
 */

public class Window {
	public Window(int width, int height, Game game) {
		JFrame frame = new JFrame("Zerro's Game");

		game.setPreferredSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		frame.add(game);
		frame.pack();
	}
}
