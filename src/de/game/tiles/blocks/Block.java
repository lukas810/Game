package de.game.tiles.blocks;

import java.awt.Graphics2D;

import de.game.graphics.Animation;
import de.game.graphics.Sprite;
import de.game.utils.Vector2;

public abstract class Block {

	protected int width;
	protected int height;

	protected Sprite image;

	protected Vector2 pos;

	protected boolean isAnimated;

	protected Animation ani;

	public Block(Sprite image, Vector2 pos, int width, int height) {
		this.image = image;
		this.pos = pos;
		this.width = width;
		this.height = height;

		isAnimated = false;

	}

	public Block(Sprite[] images, Vector2 pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;

		isAnimated = true;
		ani = new Animation(images);
	}

	public abstract boolean update();

	public void render(Graphics2D g) {
//		if (isAnimated) {
//			g.drawImage(ani.getImage().image, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), width,
//					height, null);
//		} else {
//			g.drawImage(image.image, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), width, height,
//					null);
//		}
	}

	public abstract Sprite getImage();

	public Vector2 getPos() {
		return pos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
