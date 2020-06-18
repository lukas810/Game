package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class Player extends Entity {

	public Player(Sprite sprite, Vector2f origin, int size) {
		super(sprite, origin, size);

		maxSpeed = 2.5f;
		acc = 0.7f;
		deacc = 0.7f;

		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setxOffset(12);
		bounds.setyOffset(40);

	}

	public void update(Enemy enemy) {
		super.update();

		if (attack && hitBounds.collides(enemy.getBounds())) {
		}

		move();
		if (!tileCollision.collisionTile(dx, 0)) {

			pos.setX(pos.getX() + dx);
			xCol = false;

		} else {
			xCol = true;
		}
		if (!tileCollision.collisionTile(0, dy)) {
			pos.setY(pos.getY() + dy);
			yCol = false;
		} else {
			yCol = true;
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getxOffset()),
				(int) (pos.getWorldVar().getY() + bounds.getyOffset()), (int) bounds.getWidth(),
				(int) bounds.getHeight());

		if (attack) {
			g.setColor(Color.RED);
			g.drawRect((int) (hitBounds.getPos().getWorldVar().getX() + hitBounds.getxOffset()),
					(int) (hitBounds.getPos().getWorldVar().getY() + hitBounds.getyOffset()),
					(int) hitBounds.getWidth(), (int) hitBounds.getHeight());
		}
		g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {

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
		//moonwalking
		if (up && down) {
			up = false;
			down = false;
		}

		if (right && left) {
			right = false;
			left = false;
		}

	}

}
