package manager;

import java.awt.Graphics;

import entity.Animation;
import screen.Tile;
import screen.TileMap;

public abstract class GameObject {
	// Tile
	protected TileMap tileMap;
	protected int tileSize;
	
	// Position and Vector
	protected double x, y;
	protected double nextX, nextY;
	protected double velX, velY;
	
	// Object ID
	protected ID id;
	
	// Object Size
	protected int width;
	protected int height;
	
	// Collision Box
	protected int cwidth;
	protected int cheight;
	
	// Collision Check
	protected boolean topRight;
	protected boolean topLeft;
	protected boolean bottomRight;
	protected boolean bottomLeft;
	
	// Direction
	protected boolean facingUp;
	protected boolean facingRight;
	protected boolean facingDown;
	protected boolean facingLeft;
	
	// Movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	
	// Movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	
	// Animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	
	public GameObject(int x, int y, ID id, TileMap tileMap) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.tileMap = tileMap;
		tileSize = tileMap.getTileSize();
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	/*
	 * Method that checks tiles surrounding player. Used for
	 * checking collision.
	 */
	public void checkBlock(double x, double y) {
		int leftTileNum = (int) (x - cwidth/2) / tileSize;
		int rightTileNum = (int) (x + cwidth/2) / tileSize;
		int topTileNum = (int) (y - cheight/2) / tileSize;
		int bottomTileNum = (int) (y + cheight/2) / tileSize;
		
		int tr = tileMap.getType(topTileNum, leftTileNum);
		int tl = tileMap.getType(topTileNum, rightTileNum);
		int br = tileMap.getType(bottomTileNum, rightTileNum);
		int bl = tileMap.getType(bottomTileNum, leftTileNum);
		
		topRight = tr == Tile.BLOCKED;
		topLeft = tl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		System.out.println("tr: " + topRight);
		System.out.println("tl: " + topLeft);
		System.out.println("br: " + bottomRight);
		System.out.println("bl: " + bottomLeft);
	}
	
	public void checkTileMapCollision() {
		nextY = y + velY;
		nextX = x + velX;
		
		checkBlock(x, nextY);
		if(velY < 0) {
			if(topLeft || topRight) {
				nextY = y;
				velY = 0;
			} else {
				nextY += velY;
			}
		}
		if(velY > 0) {
			if(bottomRight || bottomLeft) {
				nextY = y;
				velY = 0;
			} else {
				nextY += velY;
			}
		}

		checkBlock(nextX, y);
		if(velX < 0) {
			if(topLeft || bottomLeft) {
				nextX = x;
				velX = 0;
			} else {
				nextX += velX;
			}
		}
		if(velX > 0) {
			if(topRight || bottomRight) {
				nextX = x;
				velX = 0;
			} else {
				nextX += velX;
			}
		}
	}
	
	public void setLeft(boolean b) {
		left = b;
		facingLeft = b;
	}

	public void setRight(boolean b) {
		right = b;
		facingRight = b;
	}

	public void setUp(boolean b) {
		up = b;
		facingUp = b;
	}

	public void setDown(boolean b) {
		down = b;
		facingDown = b;
	}
	
	/*
	 * Position Getter/Setter
	 * 
	 * Note: x, y value passed in is using nextX and nextY
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	/*
	 * Vector Getter/Setter
	 */
	public void setVector(double velX, double velY) {
		this.velX = velX;
		this.velY = velY;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	/*
	 * ID Getter/Setter
	 */
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return id;
	}
	

}
