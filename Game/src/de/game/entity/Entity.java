package de.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.game.graphics.Animation;
import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.TileCollision;
import de.game.utils.Vector2f;

public abstract class Entity {

	private static final int UP = 3;
	private static final int DOWN = 2;
	private static final int RIGHT = 0;
	private static final int LEFT = 1;

	protected Animation ani;
	protected Sprite sprite;
	protected Vector2f pos;
	protected int size;
	protected int currentAnimation;

	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;
	protected boolean attack;
	protected int attackSpeed;

	protected float dx;
	protected float dy;
	protected float maxSpeed;
	protected float acc;
	protected float deacc;

	protected AABB hitBounds;
	protected AABB bounds;
	
	protected TileCollision tileCollision;
	

	public Entity(Sprite sprite, Vector2f origin, int size) {
		this.sprite = sprite;
		pos = origin;
		this.size = size;

		bounds = new AABB(origin, size, size);
		hitBounds = new AABB(new Vector2f(origin.getX() + (size / 2), origin.getY()), size, size);

		ani = new Animation();
		setAnimation(DOWN, sprite.getSpriteArrayAtIndex(DOWN), 10);
		
		tileCollision = new TileCollision(this);
	}
	
	public abstract void render(Graphics2D g);

	public void input(KeyHandler key, MouseHandler mouse) {
		
	}

	public void setAnimation(int i, BufferedImage[] frames, int delay) {
		currentAnimation = i;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}

	public void update() {
		animate();
		setHitboxDirection();
		ani.update();
	}

	public void animate() {
		if (up) {
			if (currentAnimation != UP || ani.getDelay() == -1) {
				setAnimation(UP, sprite.getSpriteArrayAtIndex(UP), 5);
			}
		} else if (down) {
			if (currentAnimation != DOWN || ani.getDelay() == -1) {
				setAnimation(DOWN, sprite.getSpriteArrayAtIndex(DOWN), 5);
			}
		} else if (right) {
			if (currentAnimation != RIGHT || ani.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getSpriteArrayAtIndex(RIGHT), 5);
			}
		} else if (left) {
			if (currentAnimation != LEFT || ani.getDelay() == -1) {
				setAnimation(LEFT, sprite.getSpriteArrayAtIndex(LEFT), 5);
			}
		} else {
			setAnimation(currentAnimation, sprite.getSpriteArrayAtIndex(currentAnimation), -1);
		}
	}
	
	private void setHitboxDirection() {
		if(up) {
			hitBounds.setyOffset(-size/2);
			hitBounds.setxOffset(-size/2);
		} else if(down) {
			hitBounds.setyOffset(size/2);
			hitBounds.setxOffset(-size/2);
		} else if(right) {
			hitBounds.setyOffset(0);
			hitBounds.setxOffset(0);
		} else if(left) {
			hitBounds.setyOffset(-size);
			hitBounds.setxOffset(0);
		}
	}

	public int getSize() {
		return size;
	}
	
	public Animation getAnimation() {
		return ani;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	

	public AABB getBounds() {
		return bounds;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setAcc(float acc) {
		this.acc = acc;
	}

	public void setDeacc(float deacc) {
		this.deacc = deacc;
	}
}
