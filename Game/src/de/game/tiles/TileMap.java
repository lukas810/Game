package de.game.tiles;

import java.awt.Graphics2D;

import de.game.tiles.blocks.Block;
import de.game.utils.AABB;

public abstract class TileMap {

	public abstract void render(Graphics2D g, AABB cam);
	
	public abstract Block[] getBlocks();
}
