package de.game.graphics;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	private int numFrames;

	private int count;
	private int delay;

	private int timesPlayed;

	public Animation() {
		timesPlayed = 0;
	}

	public Animation(BufferedImage[] frames) {
		setFrames(frames);
		timesPlayed = 0;
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		numFrames = frames.length;
	}

	public void update() {
		if (delay == -1) {
			return;
		}

		count++;

		if (count == delay) {
			currentFrame++;
			count = 0;
		}

		if (currentFrame == numFrames) {
			currentFrame = 0;
			timesPlayed++;
		}
	}

	public BufferedImage getImage() {
		return frames[currentFrame];
	}

	public boolean hasPlayedOnce() {
		return timesPlayed > 0;
	}
	
	public boolean hasPlayedTimes(int i) {
		return timesPlayed == i;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public void setNumFrames(int numFrames) {
		this.numFrames = numFrames;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

	public BufferedImage[] getFrames() {
		return frames;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public int getNumFrames() {
		return numFrames;
	}

	public int getCount() {
		return count;
	}

	public int getDelay() {
		return delay;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

}
