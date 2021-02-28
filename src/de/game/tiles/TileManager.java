package de.game.tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.game.graphics.SpriteSheet;
import de.game.tiles.blocks.NormBlock;

public class TileManager {

	public static ArrayList<TileMap> tilemap;

//	private Camera camera;

	private int width;
	private int height;

	public TileManager(String path) {
		tilemap = new ArrayList<TileMap>();
		addTileMap(path, 16, 16);
	}

	private void addTileMap(String path, int blockWidth, int blockHeight) {
		String imagePath;
		int width = 0;
		int height = 0;
		int tileWidth;
		int tileHeight;
		int tileColumns;
		int layers = 0;
		SpriteSheet sprite;


		String[] data = new String[3];
		int[] animatedTileIDs;

		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(getClass().getClassLoader().getResourceAsStream(path));
			document.getDocumentElement().normalize();
			NodeList list = document.getElementsByTagName("tileset");

			Node node = list.item(0);

			Element element = (Element) node;

			imagePath = element.getAttribute("name");
			tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
			tileColumns = Integer.parseInt(element.getAttribute("columns"));

			sprite = new SpriteSheet("tile/" + imagePath + ".png", tileWidth, tileHeight);

			list = document.getElementsByTagName("tile");
			node = list.item(0);
			element = (Element) node;
			int animatedTileID = Integer.parseInt(element.getAttribute("id"));

			list = document.getElementsByTagName("frame");
			animatedTileIDs = new int[list.getLength()];

			for (int i = 0; i < list.getLength(); i++) {
				node = list.item(i);
				element = (Element) node;
				animatedTileIDs[i] = Integer.parseInt(element.getAttribute("tileid"));
			}

			int duration = Integer.parseInt(element.getAttribute("duration"));
			
			AnimationTileData animationData = new AnimationTileData(animatedTileID, animatedTileIDs, duration);

			list = document.getElementsByTagName("layer");
			layers = list.getLength();

			for (int i = 0; i < layers; i++) {

				node = list.item(i);
				element = (Element) node;

				if (i <= 0) {
					width = Integer.parseInt(element.getAttribute("width"));
					height = Integer.parseInt(element.getAttribute("height"));
				}

				data[i] = element.getElementsByTagName("data").item(0).getTextContent();

				if (i >= 1) {
					tilemap.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns,
							animationData));
				} else {
					tilemap.add(
							new TileMapObject(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public NormBlock[] getNormalTile(int id) {
		int normMap = 1;
		if (tilemap.size() < 2)
			normMap = 0;
		NormBlock[] block = new NormBlock[9];

		int i = 0;
		for (int x = 1; x > -2; x--) {
			for (int y = 1; y > -2; y--) {
				if (id + (y + x * height) < 0 || id + (y + x * height) > (width * height) - 2)
					continue;
				block[i] = (NormBlock) tilemap.get(normMap).getBlocks()[id + (y + x * height)];
				i++;
			}
		}

		return block;
	}

	public void render(Graphics2D g) {
//		if (camera == null) {
//			return;
//		}
//		for (int i = 0; i < tilemap.size(); i++) {
//			tilemap.get(i).render(g, camera.getBounds());
//		}
	}

	public void update() {
//		if (camera == null) {
//			return;
//		}
//		for (int i = 0; i < tilemap.size(); i++) {
//			tilemap.get(i).update(camera.getBounds());
//		}
	}
}
