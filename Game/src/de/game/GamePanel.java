package de.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.game.states.GameStateManager;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;

	public static int oldFrameCount;

	private Thread thread;
	private boolean running = false;

	private BufferedImage img;
	private Graphics2D g;

	private MouseHandler mouse;
	private KeyHandler key;

	private GameStateManager gsm;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	private void init() {
		running = true;
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();

		mouse = new MouseHandler(this);
		key = new KeyHandler(this);

		gsm = new GameStateManager();
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

		oldFrameCount = 0;

		while (running) {

			double now = System.nanoTime();
			int updateCount = 0;

			while ((now - lastUpdateTime) > TBU && (updateCount < MUBR)) {
				update();
				input(mouse, key);
				lastUpdateTime += TBU;
				updateCount++;

			}

			if (now - lastUpdateTime > TBU) {
				lastUpdateTime = now - TBU;
			}

			input(mouse, key);
			render();
			draw();

			lastRenderTime = now;
			frameCount++;

			int thisSecond = (int) (lastUpdateTime / 1000000000);

			if (thisSecond > lastSecondTime) {
				if (frameCount != oldFrameCount) {
					oldFrameCount = frameCount;
				}
				frameCount = 0;
				lastSecondTime = thisSecond;
			}

			while ((now - lastRenderTime) < TTBR && (now - lastUpdateTime) < TBU) {
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

	private void update() {
		gsm.update();
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}

	private void render() {
		if (g != null) {
			g.setColor(new Color(66, 134, 244));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			gsm.render(g);
		}
	}

	private void draw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

}
