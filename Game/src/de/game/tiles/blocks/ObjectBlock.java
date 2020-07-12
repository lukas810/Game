package de.game.tiles.blocks;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.Vector2f;

public class ObjectBlock extends Block {

	public ObjectBlock(Sprite image, Vector2f pos, int width, int height) {
		super(image, pos, width, height);
	}

	@Override
	public boolean update(AABB p) {
		return true;
	}

	public void render(Graphics2D g) {
		super.render(g);

	}

	@Override
	public Sprite getImage() {
		return image;
	}
}
