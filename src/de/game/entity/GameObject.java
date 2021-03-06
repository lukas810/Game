package de.game.entity;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.graphics.SpriteSheet;
import de.game.utils.AABB;
import de.game.utils.TileCollision;
import de.game.utils.Vector2f;

public abstract class GameObject {

	protected SpriteSheet sprite;
	protected Sprite image;
	protected AABB bounds;
	protected Vector2f pos;
	protected int size;
	protected int spriteX;
	protected int spriteY;

	protected float dx;
	protected float dy;

	protected float maxSpeed = 4f;
	protected float acc = 2f;
	protected float deacc = 0.3f;
	protected float force = 15f;

	protected TileCollision tc;

	public GameObject(SpriteSheet sprite, Vector2f origin, int spriteX, int spriteY, int size) {
		this(origin, size);
		this.sprite = sprite;
	}

	public GameObject(Sprite image, Vector2f origin, int size) {
		this(origin, size);
		this.image = image;
	}

	private GameObject(Vector2f origin, int size) {
		this.bounds = new AABB(origin, size, size);
		this.pos = origin;
		this.size = size;
	}

	public void addForce(float a, boolean vertical) {
		if (!tc.tileCollision(dx, 0) && !tc.tileCollision(0, dy)) {
			if (!vertical) {
				dx -= a;
			} else {
				dy -= a;
			}
		}
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
		this.bounds = new AABB(pos, size, size);
	}

	public void render(Graphics2D g) {
		g.drawImage(image.image, (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
	}

	public Sprite getImage() {
		return image;
	}

	public void setSprite(SpriteSheet sprite) {
		this.sprite = sprite;
	}

	public void setSize(int i) {
		size = i;
	}

	public void setMaxSpeed(float f) {
		maxSpeed = f;
	}

	public void setAcc(float f) {
		acc = f;
	}

	public void setDeacc(float f) {
		deacc = f;
	}

	public float getDeacc() {
		return deacc;
	}

	public float getAcc() {
		return acc;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}

	public AABB getBounds() {
		return bounds;
	}

	public Vector2f getPos() {
		return pos;
	}

	public int getSize() {
		return size;
	}
}
