package de.game.tiles;

import java.awt.Graphics2D;

import de.game.graphics.SpriteSheet;
import de.game.tiles.blocks.Block;
import de.game.tiles.blocks.ObjectBlock;
import de.game.utils.AABB;
import de.game.utils.Vector2f;

public class TileMapObject extends TileMap {

	public static Block[] eventBlocks;

	private int tileWidth;
	private int tileHeight;

	public static int width;
	public static int height;

	public TileMapObject(String data, SpriteSheet sprite, int width, int height, int tileWidth, int tileHeight,
			int tileColumns) {

		Block tempBlock;

		eventBlocks = new Block[width * height];

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;

		TileMapObject.width = width;
		TileMapObject.height = height;

		String[] block = data.split(",");

		for (int i = 0; i < (width * height); i++) {
			int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
			if (temp != 0) {

				tempBlock = new ObjectBlock(
						sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
						new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth,
						tileHeight);

				eventBlocks[i] = tempBlock;
			}
		}

	}

	@Override
	public void render(Graphics2D g, AABB cam) {
		int x = (int) ((cam.getPos().getX()) / tileWidth);
		int y = (int) ((cam.getPos().getY()) / tileHeight);

		for (int i = x; i < x + (cam.getWidth() / tileWidth); i++) {
			for (int j = y; j < y + (cam.getHeight() / tileHeight); j++) {
				if (i + (j * height) > -1 && i + (j * height) < eventBlocks.length
						&& eventBlocks[i + (j * height)] != null) {

					eventBlocks[i + (j * height)].render(g);
				}
			}
		}
	}

	public void update(AABB cam) {
		int x = (int) ((cam.getPos().getX()) / tileWidth);
		int y = (int) ((cam.getPos().getY()) / tileHeight);

		for (int i = x; i < x + (cam.getWidth() / tileWidth); i++) {
			for (int j = y; j < y + (cam.getHeight() / tileHeight); j++) {
				if (i + (j * height) > -1 && i + (j * height) < eventBlocks.length
						&& eventBlocks[i + (j * height)] != null) {
					eventBlocks[i + (j * height)].update(null);
				}
			}
		}
	}

	@Override
	public Block[] getBlocks() {
		return eventBlocks;
	}
}
