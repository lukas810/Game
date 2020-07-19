package de.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
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
	public static int oldTickCount;
	public static int tickCount;

	private Thread thread;
	private boolean running = false;

	private BufferedImage img;
	private Graphics2D g;
	private BufferStrategy bs;

	private MouseHandler mouse;
	private KeyHandler key;

	private GameStateManager gsm;

	public GamePanel(BufferStrategy bs) {
		this.bs = bs;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void initGraphics() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	private void init() {
		running = true;

		initGraphics();

		mouse = new MouseHandler(this);
		key = new KeyHandler(this);

		gsm = new GameStateManager(g);
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

		tickCount = 0;
		oldTickCount = 0;

		while (running) {

			double now = System.nanoTime();
			int updateCount = 0;

			while ((now - lastUpdateTime) > TBU && (updateCount < MUBR)) {
				update(now);
				input(mouse, key);
				lastUpdateTime += TBU;
				updateCount++;
				tickCount++;

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
				
				 if (tickCount != oldTickCount) {
	                    oldTickCount = tickCount;
	                }
				 tickCount = 0;
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

	private void update(double time) {
		gsm.update(time);
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}

	private void render() {
		if (g != null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			gsm.render(g);
		}
	}

	private void draw() {
		do {
			Graphics g2 = (Graphics) bs.getDrawGraphics();
			g2.drawImage(img, 3, 26, WIDTH + 10, HEIGHT + 10, null);
			g2.dispose();
			bs.show();
		} while (bs.contentsLost());

	}

}
