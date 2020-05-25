package de.game.entity;

import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class Player extends Entity {

	public Player(Sprite sprite, Vector2f origin, int size) {
		super(sprite, origin, size);

	}

	@Override
	public void update() {
		super.update();
		move();

		pos.setX(pos.getX() + dx);
		pos.setY(pos.getY() + dy);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(ani.getImage(), (int) pos.getX(), (int) pos.getY(), size, size, null);
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		if (key.up.down) {
			up = true;
		} else {
			up = false;
		}
		if (key.down.down) {
			down = true;
		} else {
			down = false;
		}
		if (key.left.down) {
			left = true;
		} else {
			left = false;
		}
		if (key.right.down) {
			right = true;
		} else {
			right = false;
		}
		if (key.attack.down) {
			attack = true;
		} else {
			attack = false;
		}

	}

	private void move() {
		if (up) {
			dy -= acc;
			if (dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else {
			if (dy < 0) {
				dy += deacc;
				if (dy > 0) {
					dy = 0;
				}
			}
		}

		if (down) {
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

		if (left) {
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
			dx += acc;
			if (dx > +maxSpeed) {
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

}
