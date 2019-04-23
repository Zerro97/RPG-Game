package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import manager.ObjectHandler;
import manager.StateHandler;
import screen.Menu;

/**
 * Main class of the entire project.
 * 
 * The class has game loop which calls update and render method of handler
 * class. The handler class will call individual update and render method of
 * game objects.
 * 
 * @author Kimhuibeom
 *
 */

public class Game extends Canvas implements Runnable, KeyListener {
	// Dimension for Main Frame
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;

	// Thread
	private Thread thread;
	private boolean isRunning;

	// Game Object Handler
	private ObjectHandler objectHandler;
 
	// Game State Handler
	private StateHandler stateHandler;

	public Game() {
		new Window(WIDTH, HEIGHT, this);

		objectHandler = new ObjectHandler();
		stateHandler = new StateHandler();
		
		addKeyListener(this);

		start();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	/*
	 * Game Loop of the game. It uses variable timestep. Optimal time represents
	 * time needed to update one frame. Update time represents time actually taken
	 * to update one frame.
	 * 
	 * By calculating the difference between optimal and actual time, we can let
	 * Thread to sleep for the exact time we are aiming for, which is 60 FPS.
	 */
	public void run() {
		this.requestFocus();

		// Game Loop
		long now;
		long updateTime;
		long wait;

		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (isRunning) {
			now = System.nanoTime();

			update();
			if(isRunning) {
				render();
			}
			
			updateTime = now - System.nanoTime();
			wait = (OPTIMAL_TIME - updateTime) / 1000000;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void update() {
		objectHandler.update();
		stateHandler.update();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		objectHandler.render(g);
		stateHandler.render(g);

		g.dispose();
		bs.show();
	}

	public void keyTyped(KeyEvent key) {
		
	}

	public void keyPressed(KeyEvent key) {
		stateHandler.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		stateHandler.keyReleased(key.getKeyCode());
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
