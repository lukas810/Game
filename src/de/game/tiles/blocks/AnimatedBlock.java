package de.game.tiles.blocks;

import de.game.graphics.Sprite;
import de.game.utils.Vector2;

public class AnimatedBlock extends Block {

	public AnimatedBlock(Sprite[] images, Vector2 pos, int width, int height, int duration) {
		super(images,pos, width, height);
		ani.setDelay(duration);
	}

	@Override
	public boolean update() {
		ani.update();
		return true;
	}

	@Override
	public Sprite getImage() {
		return image;
	}
}
