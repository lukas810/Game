package de.game.graphics;

import java.awt.image.BufferedImage;

public class Sprite {

	public BufferedImage image;

	private int height;
	private int width;

	public Sprite(BufferedImage image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public Sprite getSubimage(int x, int y, int w, int h) {
		return new Sprite(image.getSubimage(x, y, w, h));
	}

	public Sprite getNewSubimage(int x, int y, int w, int h) {
		BufferedImage temp = image.getSubimage(x, y, w, h);
		BufferedImage newImage = new BufferedImage(image.getColorModel(),
				image.getRaster().createCompatibleWritableRaster(w, h), image.isAlphaPremultiplied(), null);
		temp.copyData(newImage.getRaster());
		return new Sprite(newImage);
	}

	public Sprite getNewSubimage() {
		return getNewSubimage(0, 0, width, height);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}