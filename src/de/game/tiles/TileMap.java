package de.game.tiles;

import java.awt.Graphics2D;

import de.game.tiles.blocks.Block;

public abstract class TileMap {

	public abstract void render(Graphics2D g);
	
	public abstract void update();
	
	public abstract Block[] getBlocks();
}
