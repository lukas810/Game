package de.game.tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.game.utils.AABB;
import de.game.utils.Vector2f;

public class HoleBlock extends Block{

	public HoleBlock(BufferedImage image, Vector2f pos, int width, int height) {
		super(image, pos, width, height);

	}

	@Override
	public boolean update(AABB p) {
		return false;
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(Color.GREEN);
		g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), width, height);
		
	}

}
