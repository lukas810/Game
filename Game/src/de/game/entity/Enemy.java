package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.Vector2f;

public class Enemy extends Entity {

	private AABB sense;
	private int radius;

	public Enemy(Sprite sprite, Vector2f origin, int size) {
		super(sprite, origin, size);

		acc = 0.5f;
		deacc = 0.7f;
		maxSpeed = 1f;
		radius = 200;

		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setxOffset(12);
		bounds.setyOffset(40);

		sense = new AABB(new Vector2f(origin.getX() + size / 2 - radius / 2, origin.getY() + size / 2 - radius / 2),
				radius);
	}

	public void update(Player player) {
		super.update();
		move(player);
		if (!tileCollision.collisionTile(dx, 0)) {
			sense.getPos().setX((sense.getPos().getX() + dx));
			pos.setX(pos.getX() + dx);

		}
		if (!tileCollision.collisionTile(0, dy)) {
			sense.getPos().setY((sense.getPos().getY() + dy));
			pos.setY(pos.getY() + dy);

		}

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getxOffset()),
				(int) (pos.getWorldVar().getY() + bounds.getyOffset()), (int) bounds.getWidth(),
				(int) bounds.getHeight());

		g.setColor(Color.WHITE);
		g.drawOval((int) sense.getPos().getWorldVar().getX(), (int) sense.getPos().getWorldVar().getY(), radius,
				radius);
		g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
	}

	private void move(Player player) {
		if (sense.collisionCircleBox(player.getBounds())) {

			if (pos.getY() > player.pos.getY()) {
				dy -= acc;
				up = true;
				down = false;
				if (dy < -maxSpeed) {
					dy = -maxSpeed;
				}
			} else if (pos.getY() < player.pos.getY()) {
				dy += acc;
				down = true;
				up = false;
				if (dy > maxSpeed) {
					dy = maxSpeed;
				}
			} else {
				dy = 0;
				up = false;
				down = false;
			}

			if (pos.getX() > player.pos.getX() -1) {
				dx -= acc;
				left = true;
				right = false;
				if (dx < -maxSpeed) {
					dx = -maxSpeed;
				}
			} else if (pos.getX() < player.pos.getX() +1 ) {
				dx += acc;
				right = true;
				left = false;
				if (dx > +maxSpeed) {
					dx = maxSpeed;
				}
			} else {
				dx = 0;
				right = false;
				left = false;
			}
		} else {
			up = false;
			down = false;
			right = false;
			left = false;
			dx = 0;
			dy = 0;
		}
	}

}
