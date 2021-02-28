package de.game.entity;

import de.game.graphics.Animation;
import de.game.graphics.SpriteSheet;
import de.game.graphics.Sprite;
import de.game.utils.Vector2;

import java.awt.Graphics2D;

public abstract class Entity extends GameObject {

	protected int RIGHT = 0;
	protected int LEFT = 1;
	protected int DOWN = 2;
	protected int UP = 3;
	protected int ATTACK = 5;
	protected int IDLE = 6;

	protected boolean up = false;
	protected boolean down = false;
	protected boolean right = false;
	protected boolean left = false;
	protected boolean attack = false;

//	protected boolean hasIdle = false;

	protected float dx;
	protected float dy;

	protected float maxSpeed = 4f;
	protected float acc = 2f;
	protected float deacc = 0.3f;

	protected int currentAnimation;
	protected int currentDirection = DOWN;
	protected Animation ani;

	public Entity(Vector2 pos, float width, float height, SpriteSheet spriteSheet) {
		super(pos, width, height, spriteSheet);

		ani = new Animation();
		setAnimation(DOWN, spriteSheet.getSpriteArray(DOWN), 10);

	}

	public Animation getAnimation() {
		return ani;
	}

	public void setAnimation(int currentAnimation, Sprite[] frames, int delay) {
		this.currentAnimation = currentAnimation;
		ani.setFrames(currentAnimation, frames);
		ani.setDelay(delay);
	}

	public void animate() {

		if (up) {
			if ((currentAnimation != UP || ani.getDelay() == -1)) {
				setAnimation(UP, spriteSheet.getSpriteArray(UP), 5);
			}
		} else if (down) {
			if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
				setAnimation(DOWN, spriteSheet.getSpriteArray(DOWN), 5);
			}
		} else if (left) {
			if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
				setAnimation(LEFT, spriteSheet.getSpriteArray(LEFT), 5);
			}
		} else if (right) {
			if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
				setAnimation(RIGHT, spriteSheet.getSpriteArray(RIGHT), 5);
			}

		} else {
			setAnimation(currentAnimation, spriteSheet.getSpriteArray(currentAnimation), -1);
		}
	}

	public void move() {
		if (up) {
			currentDirection = UP;
			dy += acc;
			if (dy > maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if (dy > 0) {
				dy -= deacc;
				if (dy < 0) {
					dy = 0;
				}
			}
		}

		if (down) {
			currentDirection = DOWN;
			dy -= acc;
			if (dy < -maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if (dy < 0) {
				dy += deacc;
				if (dy > 0) {
					dy = 0;
				}
			}
		}

		if (left) {
			currentDirection = LEFT;
			dx -= acc;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else {
			if (dx < 0) {
				dx += deacc;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if (right) {
			currentDirection = RIGHT;
			dx += acc;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= deacc;
				if (dx < 0) {
					dx = 0;
				}
			}
		}
	}

	public void update(double time) {
		animate();
		ani.update();
	}

	@Override
	public abstract void render(Graphics2D g);

}
