package de.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;

	private Thread thread;
	private BufferedImage img;
	private Graphics2D g;
	private boolean running = false;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	private void init() {
		running = true;
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
	}

	@Override
	public void addNotify() {
		super.addNotify();

		if (thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}

	@Override
	public void run() {
		init();

		final double GAME_HERTZ = 60.0;
		final double TBU = 1000000000 / GAME_HERTZ; // time before update
		final double MUBR = 5; // must update before render

		double lastUpdateTime = System.nanoTime();
		double lastRenderTime;

		final double TARGET_FPS = 60;
		final double TTBR = 1000000000 / TARGET_FPS; // total time before render

		int frameCount = 0;

		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		int oldFrameCount = 0;

		while (running) {

			double now = System.nanoTime();
			int updateCount = 0;

			while ((now - lastUpdateTime) > TBU && (updateCount < MUBR)) {
				update();
				input();
				lastUpdateTime += TBU;
				updateCount++;

			}
			
			if(now - lastUpdateTime > TBU) {
				lastUpdateTime = now - TBU;
			}
			
			input();
			render();
			draw();
			
			lastRenderTime = now;
			frameCount++;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			
			if(thisSecond> lastSecondTime) {
				if(frameCount != oldFrameCount) {
					System.out.println("new second " + thisSecond + " " + frameCount);
					oldFrameCount = frameCount;
				}
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			
			while((now - lastRenderTime) < TTBR && (now - lastUpdateTime) < TBU) {
				Thread.yield();
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				now = System.nanoTime();
			}

		}
	}

	public void input() {
		
	}
	private void update() {

	}

	private void render() {
		if (g != null) {
			g.setColor(new Color(66, 134, 244));
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
	}

	private void draw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

}
