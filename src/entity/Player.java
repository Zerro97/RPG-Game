package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import manager.GameObject;
import manager.ID;
import screen.TileMap;

/**
 * Player object.
 * 
 * Since it extends gameObject, it has fields regarding size, location,
 * collision box, direction and velocity
 * 
 * @author Kimhuibeom
 *
 */
public class Player extends GameObject {
	// Player Current Info
	private int health;
	private int mana;
	private int experience;

	// Player Info
	private int maxHealth;
	private int maxMana;
	private int maxExperience;

	// Player Status
	private boolean dead;
	private boolean flinching;

	// Player Actions
	private static final int DOWN = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int UP = 3;
	private static final int IDLE = 4;

	// Player Animation Frames
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 3, 3, 3, 3 };

	public Player(int x, int y, ID id, TileMap tileMap) {
		super(x, y, id, tileMap);

		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;

		velX = 0;
		velY = 0;
		moveSpeed = 1;
		maxSpeed = 3;

		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/S_Human_1.png"));

			sprites = new ArrayList<BufferedImage[]>();

			for (int i = 0; i < 4; i++) {
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for (int j = 0; j < numFrames[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				}
				sprites.add(bi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = DOWN;
		animation.setFrames(sprites.get(DOWN));
		animation.setDelay(150);
	}

	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(nextX, nextY);

		if (up) {
			if (currentAction != UP) {
				currentAction = UP;
				animation.setFrames(sprites.get(UP));
				animation.setDelay(150);
			}
		} else if (right) {
			if (currentAction != RIGHT) {
				currentAction = RIGHT;
				animation.setFrames(sprites.get(RIGHT));
				animation.setDelay(150);
			}
		} else if (down) {
			if (currentAction != DOWN) {
				currentAction = DOWN;
				animation.setFrames(sprites.get(DOWN));
				animation.setDelay(150);
			}
		} else if (left) {
			if (currentAction != LEFT) {
				currentAction = LEFT;
				animation.setFrames(sprites.get(LEFT));
				animation.setDelay(150);
			}
		} else {
			return;
		}
		
		animation.update();
	}

	public void render(Graphics g) {
		g.drawImage(animation.getImage(), (int) x - width / 2, (int) y - height / 2, null);
	}

	public void getNextPosition() {
		if (up) {
			velY -= moveSpeed;
			if(velY < - maxSpeed) {
				velY = -maxSpeed;
			}
		} 
		if (right) {
			velX += moveSpeed;
			if(velX > maxSpeed) {
				velX = maxSpeed;
			}
		} 
		if (down) {
			velY += moveSpeed;
			if(velY > maxSpeed) {
				velY = maxSpeed;
			}
		} 
		if (left) {
			velX -= moveSpeed;
			if(velX < - maxSpeed) {
				velX = -maxSpeed;
			}
		}
		if (!up && !down) {
			velY = 0;
		}
		if (!right && !left) {
			velX = 0;
		}
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMana() {
		return mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public int getExperience() {
		return experience;
	}

	public int getMaxExperience() {
		return maxExperience;
	}
}
