package de.game.tiles;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.graphics.SpriteSheet;
import de.game.tiles.blocks.AnimatedBlock;
import de.game.tiles.blocks.Block;
import de.game.tiles.blocks.NormBlock;
import de.game.utils.Vector2;

public class TileMapNorm extends TileMap {

	private Block[] blocks;

	private int tileWidth;
	private int tileHeight;

	private int height;

	public TileMapNorm(String data, SpriteSheet sprite, int width, int height, int tileWidth, int tileHeight,
			int tileColumns, AnimationTileData animationData) {

		blocks = new Block[width * height];

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;

		this.height = height;

		String[] block = data.split(",");

		for (int i = 0; i < (width * height); i++) {
			int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
			if (temp != 0) {
				if (temp == 4517) {
					Sprite[] images = new Sprite[animationData.getAnimatedTileIDs().length];
					for (int j = 0; j < animationData.getAnimatedTileIDs().length; j++) {
						images[j] = sprite.getNewSprite((int) ((animationData.getAnimatedTileIDs()[j] - 1) % tileColumns),
								(int) ((animationData.getAnimatedTileIDs()[j] - 1) / tileColumns));
					}
					blocks[i] = new AnimatedBlock(images,
							new Vector2((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth,
							tileHeight,animationData.getDuration());
				} else {

					blocks[i] = new NormBlock(
							sprite.getNewSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
							new Vector2((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth,
							tileHeight);

				}
			}
		}

	}

	@Override
	public void render(Graphics2D g) {
//		int x = (int) ((cam.getPos().getX()) / tileWidth);
//		int y = (int) ((cam.getPos().getY()) / tileHeight);
//
//		for (int i = x; i < x + (cam.getWidth() / tileWidth); i++) {
//			for (int j = y; j < y + (cam.getHeight() / tileHeight); j++) {
//				if (i + (j * height) > -1 && i + (j * height) < blocks.length && blocks[i + (j * height)] != null) {
//					blocks[i + (j * height)].render(g);
//				}
//			}
//		}
	}
	
	public void update() {
//		int x = (int) ((cam.getPos().getX()) / tileWidth);
//		int y = (int) ((cam.getPos().getY()) / tileHeight);
//
//		for (int i = x; i < x + (cam.getWidth() / tileWidth); i++) {
//			for (int j = y; j < y + (cam.getHeight() / tileHeight); j++) {
//				if (i + (j * height) > -1 && i + (j * height) < blocks.length && blocks[i + (j * height)] != null) {
//					blocks[i + (j * height)].update();
//				}
//			}
//		}
	}

	@Override
	public Block[] getBlocks() {
		return blocks;
	}
	
}
