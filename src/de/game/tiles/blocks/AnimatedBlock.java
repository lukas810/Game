package de.game.tiles.blocks;

import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.Vector2f;

public class AnimatedBlock extends Block {

	public AnimatedBlock(Sprite[] images, Vector2f pos, int width, int height) {
		super(images,pos, width, height);
		ani.setDelay(100);
	}

	@Override
	public boolean update(AABB p) {
		ani.update();
		return true;
	}

	@Override
	public Sprite getImage() {
		return image;
	}
}
