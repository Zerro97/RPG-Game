package manager;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Game Handler that handles each and every game objects in the game.
 * The list of game objects are made using linked list.
 * 
 * Using this handler, we can:
 * 1) Add and remove object from the game
 * 2) Call individual object's update and render method
 * 
 * @author Kimhuibeom
 *
 */

public class ObjectHandler {
	private LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	public void update() {
		for(int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);
			tempObject.update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objectList.remove(object);
	}
}
