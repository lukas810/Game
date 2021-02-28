package de.game.entity;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.graphics.SpriteSheet;
import de.game.utils.Vector2;

public class GameObject {

	protected Vector2 pos;
	protected Sprite sprite;
	protected SpriteSheet spriteSheet;
	protected float width;
	protected float height;

	public GameObject(Vector2 pos, float width, float height, Sprite sprite) {
		this.pos = pos;
		this.sprite = sprite;
		this.width = width;
		this.height = height;
	}
	
	public GameObject(Vector2 pos, float width, float height, SpriteSheet spriteSheet) {
		this.pos = pos;
		this.spriteSheet = spriteSheet;
		this.width = width;
		this.height = height;
	}

	public void render(Graphics2D g) {
		g.drawImage(sprite.image, (int) pos.x, (int) pos.y, (int) width, (int) height, null);
	}
}