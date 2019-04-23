package manager;

import java.awt.Graphics;
import java.util.ArrayList;

import screen.GameScreen;
import screen.Menu;

public class StateHandler {
	private ArrayList<GameState> stateList;

	private int currentState;

	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;
	public static final int HELPSTATE = 2;
	public static final int SETTINGSTATE = 3;
	
	public StateHandler() {
		stateList = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		stateList.add(new Menu(this));
		stateList.add(new GameScreen(this));
	}
	
	public void setState(int state) {
		currentState = state;
	}
	
	public void update() {
		stateList.get(currentState).update();
	}

	public void render(Graphics g) {
		stateList.get(currentState).render(g);
	}
	
	public void keyPressed(int k) {
		stateList.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) {
		stateList.get(currentState).keyReleased(k);
	}
}
