package de.game.tiles;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.game.graphics.Sprite;

public class TileManager {

	public static ArrayList<TileMap> tilemap;

	public TileManager() {
		tilemap = new ArrayList<TileMap>();
	}

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
		Sprite sprite;

		String[] data = new String[3];
		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
			document.getDocumentElement().normalize();
			NodeList list = document.getElementsByTagName("tileset");

			Node node = list.item(0);

			Element element = (Element) node;

			imagePath = element.getAttribute("name");
			tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
			tileColumns = Integer.parseInt(element.getAttribute("columns"));

			list = document.getElementsByTagName("image");

			sprite = new Sprite("tile/" + imagePath + ".png", tileWidth, tileHeight);

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
					tilemap.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				} else {
					tilemap.add(
							new TileMapObject(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void render(Graphics2D g) {
		for (int i = 0; i < tilemap.size(); i++) {
			tilemap.get(i).render(g);
		}
	}
}
