package de.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Font {

	private BufferedImage fontsheet;
	private BufferedImage[][] fontArray;
	private final int tileSize = 32;
	public int height;
	public int width;
	private int letterHeight;
	private int letterWidth;

	public Font(String file) {

		width = tileSize;
		height = tileSize;

		fontsheet = loadFont(file);
		loadFontArray();
	}

	public Font(String file, int width, int height) {

		this.width = width;
		this.height = height;

		fontsheet = loadFont(file);

		letterWidth = fontsheet.getWidth() / width;
		letterHeight = fontsheet.getHeight() / height;
		loadFontArray();
	}

	private BufferedImage loadFont(String file) {
		BufferedImage font = null;
		try {
			font = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return font;
	}

	public void loadFontArray() {
		fontArray = new BufferedImage[letterWidth][letterHeight];

		for (int x = 0; x < letterWidth; x++) {
			for (int y = 0; y < letterHeight; y++) {
				fontArray[x][y] = getLetter(x, y);
			}
		}
	}

	public BufferedImage getLetter(int x, int y) {
		return fontsheet.getSubimage(x * width, y * height, width, height);
	}

	public BufferedImage getFont(char letter) {
		int value = letter - 65;

		int x = value % letterWidth;
		int y = value / letterWidth;

		return getLetter(x, y);
	}

	public BufferedImage[] getFontArrayAtIndex(int i) {
		return fontArray[i];
	}

	public BufferedImage[][] getFontArray2() {
		return fontArray;
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setHeight(int height) {
		this.height = height;
		letterHeight = fontsheet.getHeight() / height;
	}

	public void setWidth(int width) {
		this.width = width;
		letterWidth = fontsheet.getWidth() / width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public BufferedImage getFontsheet() {
		return fontsheet;
	}

}
