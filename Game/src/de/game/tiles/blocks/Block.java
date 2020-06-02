package de.game.tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.game.utils.AABB;
import de.game.utils.Vector2f;

public abstract class Block {

	protected int width;
	protected int height;

	protected BufferedImage image;

	protected Vector2f pos;

	public Block(BufferedImage image, Vector2f pos, int width, int height) {
		this.image = image;
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	public abstract boolean update(AABB p);

	public void render(Graphics2D g) {
		g.drawImage(image, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), width, height, null);
	}
}
