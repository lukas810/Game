package de.game.tiles.blocks;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.utils.Vector2;

public class NormBlock extends Block {

	public NormBlock(Sprite image, Vector2 pos, int width, int height) {
		super(image, pos, width, height);

	}

	@Override
	public boolean update() {
		return false;
	}

	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public Sprite getImage() {
		return image;
	}
}
