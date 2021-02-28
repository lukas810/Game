package de.game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.game.utils.Vector2;

public class SpriteSheet {

	private Sprite spritesheet = null;
	private Sprite[][] spriteArray;
	private final int TILE_SIZE = 32;
	public int width;
	public int height;
	private int wSprite;
	private int hSprite;
	private String file;

	public static Font currentFont;

	public SpriteSheet(String file) {
		this.file = file;
		width = TILE_SIZE;
		height = TILE_SIZE;

		spritesheet = new Sprite(loadSprite(file));

		wSprite = spritesheet.image.getWidth() / width;
		hSprite = spritesheet.image.getHeight() / height;
		loadSpriteArray();
	}

	public SpriteSheet(Sprite sprite, String name, int width, int height) {
		this.width = width;
		this.height = height;
		
		spritesheet = sprite;

		wSprite = spritesheet.image.getWidth() / this.width;
		hSprite = spritesheet.image.getHeight() / this.height;
		loadSpriteArray();

	}

	public SpriteSheet(String file, int width, int height) {
		this.width = width;
		this.height = height;
		this.file = file;

		spritesheet = new Sprite(loadSprite(file));

		wSprite = spritesheet.image.getWidth() / this.width;
		hSprite = spritesheet.image.getHeight() / this.height;
		loadSpriteArray();
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setWidth(int i) {
		width = i;
		wSprite = spritesheet.image.getWidth() / width;
	}

	public void setHeight(int i) {
		height = i;
		hSprite = spritesheet.image.getHeight() / height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRows() {
		return hSprite;
	}

	public int getCols() {
		return wSprite;
	}

	public int getTotalTiles() {
		return wSprite * hSprite;
	}

	public String getFilename() {
		return file;
	}

	private BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch (Exception e) {
			System.out.println("ERROR: could not load file: " + file);
		}
		return sprite;
	}

	public void loadSpriteArray() {
		spriteArray = new Sprite[hSprite][wSprite];

		for (int y = 0; y < hSprite; y++) {
			for (int x = 0; x < wSprite; x++) {
				spriteArray[y][x] = getSprite(x, y);
			}
		}
	}

	public Sprite getSpriteSheet() {
		return spritesheet;
	}

	public Sprite getSprite(int x, int y) {
		return spritesheet.getSubimage(x * width, y * height, width, height);
	}

	public Sprite getNewSprite(int x, int y) {
		return spritesheet.getNewSubimage(x * width, y * height, width, height);
	}

	public Sprite getSprite(int x, int y, int w, int h) {
		return spritesheet.getSubimage(x * w, y * h, w, h);
	}

	public BufferedImage getSubimage(int x, int y, int w, int h) {
		return spritesheet.image.getSubimage(x, y, w, h);
	}

	public Sprite[] getSpriteArray(int i) {
		return spriteArray[i];
	}

	public Sprite[][] getSpriteArray2() {
		return spriteArray;
	}

	public static void drawArray(Graphics2D g, ArrayList<Sprite> img, Vector2 pos, int width, int height, int xOffset,
			int yOffset) {
		float x = pos.getX();
		float y = pos.getY();

		for (int i = 0; i < img.size(); i++) {
			if (img.get(i) != null) {
				g.drawImage(img.get(i).image, (int) x, (int) y, width, height, null);
			}

			x += xOffset;
			y += yOffset;
		}
	}

	public static void drawArray(Graphics2D g, String word, Vector2 pos, int size) {
		drawArray(g, currentFont, word, pos, size, size, size, 0);
	}

	public static void drawArray(Graphics2D g, String word, Vector2 pos, int size, int xOffset) {
		drawArray(g, currentFont, word, pos, size, size, xOffset, 0);
	}

	public static void drawArray(Graphics2D g, String word, Vector2 pos, int width, int height, int xOffset) {
		drawArray(g, currentFont, word, pos, width, height, xOffset, 0);
	}

	public static void drawArray(Graphics2D g, Font f, String word, Vector2 pos, int size, int xOffset) {
		drawArray(g, f, word, pos, size, size, xOffset, 0);
	}

	public static void drawArray(Graphics2D g, Font f, String word, Vector2 pos, int width, int height, int xOffset,
			int yOffset) {
		float x = pos.getX();
		float y = pos.getY();

		currentFont = f;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != 32)
				g.drawImage(f.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);

			x += xOffset;
			y += yOffset;
		}

	}

}
