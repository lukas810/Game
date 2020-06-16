package de.game.utils;

import de.game.entity.Entity;
import de.game.tiles.TileMapObject;
import de.game.tiles.blocks.Block;

public class TileCollision {

	private Entity entity;

	public TileCollision(Entity entity) {
		this.entity = entity;

	}

	public boolean collisionTile(float ax, float ay) {
		for (int i = 0; i < 4; i++) {
			int xt = (int) ((entity.getBounds().getPos().getX() + ax) + (i % 2) * entity.getBounds().getWidth()
					+ entity.getBounds().getxOffset()) / 16;
			int yt = (int) ((entity.getBounds().getPos().getY() + ay) + (i / 2) * entity.getBounds().getHeight()
					+ entity.getBounds().getyOffset()) / 16;
			
			if(TileMapObject.eventBlocks[xt + (yt * TileMapObject.height)] instanceof Block) {
                Block block = TileMapObject.eventBlocks[xt + (yt * TileMapObject.height)];
                
                return block.update(entity.getBounds());
			}
			
		}
		return false;
	}

}
