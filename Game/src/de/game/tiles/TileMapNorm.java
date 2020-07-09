package de.game.tiles;

import java.awt.Graphics2D;

import de.game.graphics.SpriteSheet;
import de.game.tiles.blocks.Block;
import de.game.tiles.blocks.NormBlock;
import de.game.utils.AABB;
import de.game.utils.Vector2f;

public class TileMapNorm extends TileMap {

	private Block[] blocks;

	private int tileWidth;
	private int tileHeight;

	private int height;

	public TileMapNorm(String data, SpriteSheet sprite, int width, int height, int tileWidth, int tileHeight,
			int tileColumns) {

		blocks = new Block[width * height];

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;

		this.height = height;

		String[] block = data.split(",");

		for (int i = 0; i < (width * height); i++) {
			int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
			if (temp != 0) {

				blocks[i] = new NormBlock(
						sprite.getNewSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
						new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth,
						tileHeight);

			}
		}

	}

	@Override
	public void render(Graphics2D g, AABB cam) {
		int x = (int) ((cam.getPos().getX()) / tileWidth);
		int y = (int) ((cam.getPos().getY()) / tileHeight);

		for (int i = x; i < x + (cam.getWidth() / tileWidth); i++) {
			for (int j = y; j < y + (cam.getHeight() / tileHeight); j++) {
				if (i + (j * height) > -1 && i + (j * height) < blocks.length && blocks[i + (j * height)] != null) {
					blocks[i + (j * height)].render(g);
				}
			}
		}
	}

	@Override
	public Block[] getBlocks() {
		return blocks;
	}
}
