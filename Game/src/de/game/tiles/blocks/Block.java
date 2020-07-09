package de.game.tiles.blocks;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.Vector2f;

public abstract class Block {

	protected int width;
	protected int height;

	protected Sprite image;

	protected Vector2f pos;

	public Block(Sprite image, Vector2f pos, int width, int height) {
		this.image = image;
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	public abstract boolean update(AABB p);

	public void render(Graphics2D g) {
		g.drawImage(image.image, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), width, height, null);
	}

	public abstract Sprite getImage();

	public Vector2f getPos() {
		return pos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
