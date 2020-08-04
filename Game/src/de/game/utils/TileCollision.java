package de.game.utils;

import de.game.entity.Entity;
import de.game.tiles.TileMapObject;
import de.game.tiles.blocks.Block;

public class TileCollision {

	private Entity entity;
	private int tileId;
	
	private final int tileSize = 16;

	public TileCollision(Entity entity) {
		this.entity = entity;

	}

	public boolean normalTile(float ax, float ay) {
		int xt;
		int yt;

		xt = (int) ((entity.getPos().getX() + ax) + entity.getBounds().getxOffset()) / tileSize;
		yt = (int) ((entity.getPos().getY() + ay) + entity.getBounds().getyOffset()) / tileSize;
		tileId = (xt + (yt * TileMapObject.height));

		if (tileId > TileMapObject.height * TileMapObject.width)
			tileId = (TileMapObject.height * TileMapObject.width) - 2;

		return false;
	}

	public boolean tileCollision(float ax, float ay) {
		if (TileMapObject.eventBlocks != null) {
			for (int i = 0; i < 4; i++) {
				int xt = (int) ((entity.getBounds().getPos().getX() + ax) + (i % 2) * entity.getBounds().getWidth()
						+ entity.getBounds().getxOffset()) / tileSize;
				int yt = (int) ((entity.getBounds().getPos().getY() + ay) + (i / 2) * entity.getBounds().getHeight()
						+ entity.getBounds().getyOffset()) / tileSize;

				if (xt <= 0 || yt <= 0 || xt + (yt * TileMapObject.height) < 0
						|| xt + (yt * TileMapObject.height) > (TileMapObject.height * TileMapObject.width) - 2) {
					return true;
				}

				if (TileMapObject.eventBlocks[xt + (yt * TileMapObject.height)] instanceof Block) {
					Block block = TileMapObject.eventBlocks[xt + (yt * TileMapObject.height)];

					return block.update(entity.getBounds());
				}

			}
		}
		return false;
	}

	public int getTile() {
		return tileId;
	}

}
