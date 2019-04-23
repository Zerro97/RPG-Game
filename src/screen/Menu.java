package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import manager.GameState;
import manager.StateHandler;

public class Menu extends GameState {
	// For setting game state
	private StateHandler handler;

	// Rendering Image
	private BufferedImage image;

	// Menu Options
	private String[] options = { "Start", "Help", "Settings", "Quit" };
	private int currentChoice = 0;

	public Menu(StateHandler handler) {
		this.handler = handler;
	}

	public void update() {

	}

	public void render(Graphics g) {
		// Draw Background
		drawBackground(g);

		// Draw Title
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 56));
		g.drawString("Zerro's Game", 160, 100);

		// Draw Menu Options
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.red);
			}
			g.drawString(options[i], 290, 170 + i * 40);
		}
	}

	private void select() {
		if (currentChoice == 0) {
			handler.setState(StateHandler.GAMESTATE);
		}
		if (currentChoice == 1) {
			handler.setState(StateHandler.HELPSTATE);
		}
		if (currentChoice == 2) {
			handler.setState(StateHandler.SETTINGSTATE);
		}
		if (currentChoice == 3) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int k) {

	}

	private void drawBackground(Graphics g) {
		try {
			image = ImageIO.read(getClass().getResource("/P_Menu_1.png"));
			g.drawImage(image, 0, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
