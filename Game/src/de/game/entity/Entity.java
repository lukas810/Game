package de.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.game.graphics.Animation;
import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public abstract class Entity {

	private final int UP = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int LEFT = 3;

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

	public Entity(Sprite sprite, Vector2f origin, int size) {
		this.sprite = sprite;
		pos = origin;
		this.size = size;

		ani = new Animation();
		setAnimation(RIGHT, sprite.getSpriteArrayAtIndex(RIGHT), 10);
	}

	public void setAnimation(int i, BufferedImage[] frames, int delay) {
		currentAnimation = i;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}

	public void update() {
		animate();
//		setHitboxDirection();
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
		}else {
			setAnimation(currentAnimation, sprite.getSpriteArrayAtIndex(currentAnimation), -1);
		}
	}
	
	public int getSize() {
		return size;
	}

	public abstract void render(Graphics2D g);

	public abstract void input(KeyHandler key, MouseHandler mouse);
}
