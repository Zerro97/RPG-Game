package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import manager.ID;

import screen.TileMap;

public class BasicEnemy extends Enemy{
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 3, 3, 3, 3 };
	
	public BasicEnemy(int x, int y, ID id, TileMap tileMap) {
		super(x, y , id, tileMap);
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Slime_1.png"));

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
	}
}
