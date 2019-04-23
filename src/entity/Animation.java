package entity;

import java.awt.image.BufferedImage;

public class Animation {
	// Image array containing different set of sprites (ex. IDLE, WALK)
	private BufferedImage[] frames;	
	
	// Index of frames array
	private int currentFrame;
	
	// Time variable which determines when to change frame
	// Both are in millisecond
	private long startTime;
	private long delay;
	
	public Animation() {
		
	}
	
	/*
	 * Loads array containing different set of sprite (ex. IDLE, WALK), set its index as 0.
	 */
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
	}
	
	public void update() {
		// Elapsed is in millisecond
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
		}
	}
	
	/*
	 * Set the index of frame array
	 */
	public void setFrame(int i) {
		currentFrame = i;
	}
	
	public int getFrame() {
		return currentFrame;
	}
	
	/*
	 * Set how long it takes to move to next frame (in millisecond)
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public BufferedImage getImage() {
		return frames[currentFrame];
	}
}
