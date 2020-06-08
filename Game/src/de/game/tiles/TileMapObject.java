package de.game.tiles;

import java.awt.Graphics2D;
import java.util.HashMap;

import de.game.graphics.Sprite;
import de.game.tiles.blocks.Block;
import de.game.tiles.blocks.ObjectBlock;
import de.game.utils.Vector2f;

public class TileMapObject extends TileMap {

	public static HashMap<String, Block> tileMapObjectBlocks;

	public TileMapObject(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight,
			int tileColumns) {

		Block tempBlock;
		tileMapObjectBlocks = new HashMap<String, Block>();

		String[] block = data.split(",");

		for (int i = 0; i < (width * height); i++) {
			int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
			if (temp != 0) {
				
				tempBlock = new ObjectBlock(
						sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
						new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth,
						tileHeight);

				tileMapObjectBlocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)),
						tempBlock);
			}
		}

	}

	public void render(Graphics2D g) {
		for (Block block : tileMapObjectBlocks.values()) {
			block.render(g);
		}
	}
}
