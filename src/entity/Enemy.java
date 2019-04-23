package entity;

import java.awt.Graphics;

import manager.GameObject;
import manager.ID;
import screen.TileMap;

/**
 * Class inherited by all the enemy classes
 * @author Kimhuibeom
 *
 */

public class Enemy extends GameObject{
	protected int health;
	protected int maxHealth;
	
	protected int damage;
	protected boolean dead;
	protected boolean flinching;
	
	public Enemy(int x, int y, ID id, TileMap tileMap) {
		super(x, y, id, tileMap);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public int getDamage() {
		return damage;
	}
	
	/*
	 * A method that calculates how much damage enemy has taken
	 */
	public void hit(int damage) {
		if(dead || flinching) {
			return;
		}
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		
	}
}
