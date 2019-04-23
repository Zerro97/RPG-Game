package screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import manager.GameState;
import manager.ID;
import manager.StateHandler;
import entity.Player;

public class GameScreen extends GameState{
	// Game Handler
	private StateHandler handler;
	
	// Game Screen
	private TileMap tileMap;
	
	// Game Objects
	private Player player;
	
	public GameScreen(StateHandler handler) {
		this.handler = handler;
		
		tileMap = new TileMap(32, 1);
		tileMap.loadTiles("/T_1.png");
		tileMap.loadMap("/Users/Kimhuibeom/eclipse-workspace/Evolution Game/Resources/M1_1.csv");
		tileMap.loadMap("/Users/Kimhuibeom/eclipse-workspace/Evolution Game/Resources/M1_2.csv");
	
		tileMap.setMapNumber(1);
		
		ID playerID = ID.player;
		player = new Player(160, 320, playerID, tileMap);
		player.setPosition(160, 160);
	}
	
	public void update() {
		player.update();
	}

	public void render(Graphics g) {
		tileMap.render(g);
		player.render(g);
	}	

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_ESCAPE) System.exit(0);
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
	}

}
