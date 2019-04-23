package manager;

import java.awt.Graphics;

public abstract class GameState {
	public abstract void update();
	public abstract void render(Graphics g);
	
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
