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
		chase(player);
		move();
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

	public void chase(Player player) {
		AABB playerBounds = player.getBounds();
		if (sense.collisionCircleBox(playerBounds)) {
			if (pos.getY() > player.pos.getY() + 1) {
				up = true;
			} else {
				up = false;
			}
			if (pos.getY() < player.pos.getY() - 1) {
				down = true;
			} else {
				down = false;
			}

			if (pos.getX() > player.pos.getX() + 1) {
				left = true;
			} else {
				left = false;
			}
			if (pos.getX() < player.pos.getX() - 1) {
				right = true;
			} else {
				right = false;
			}
		} else {
			up = false;
			down = false;
			left = false;
			right = false;
		}
	}

}
