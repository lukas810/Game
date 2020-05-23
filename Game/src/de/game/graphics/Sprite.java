package de.game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.game.utils.Vector2f;

public class Sprite {

	private final BufferedImage spritesheet;
	private BufferedImage[][] spriteArray;
	private final int tileSize = 32;
	public int height;
	public int width;
	private int spriteHeight;
	private int spriteWidth;

	public Sprite(String file) {

		width = tileSize;
		height = tileSize;

		spritesheet = loadSprite(file);
		loadSpriteArray();
	}

	public Sprite(String file, int width, int height) {

		this.width = width;
		this.height = height;

		spritesheet = loadSprite(file);

		spriteWidth = spritesheet.getWidth() / width;
		spriteHeight = spritesheet.getHeight() / height;
		loadSpriteArray();
	}

	private BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public void loadSpriteArray() {
		spriteArray = new BufferedImage[spriteWidth][spriteHeight];

		for (int x = 0; x < spriteWidth; x++) {
			for (int y = 0; y < spriteHeight; y++) {
				spriteArray[x][y] = getSprite(x, y);
			}
		}
	}

	public BufferedImage getSprite(int x, int y) {
		return spritesheet.getSubimage(x * width, y * height, width, height);
	}

	public BufferedImage[] getSpriteArrayAtIndex(int i) {
		return spriteArray[i];
	}

	public BufferedImage[][] getSpriteArray2() {
		return spriteArray;
	}

	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height,
			int xOffset, int yOffset) {

		float x = pos.getX();
		float y = pos.getY();

		for (int i = 0; i < img.size(); i++) {
			if (img.get(i) != null)
				g.drawImage(img.get(i), (int) x, (int) y, width, height, null);

			x += xOffset;
			y += yOffset;
		}

	}

	public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset,
			int yOffset) {

		float x = pos.getX();
		float y = pos.getY();

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != 32) {
				g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
			}
			x += xOffset;
			y += yOffset;
		}

	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setHeight(int height) {
		this.height = height;
		spriteHeight = spritesheet.getHeight() / height;
	}

	public void setWidth(int width) {
		this.width = width;
		spriteWidth = spritesheet.getWidth() / width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public BufferedImage getSpritesheet() {
		return spritesheet;
	}

}
