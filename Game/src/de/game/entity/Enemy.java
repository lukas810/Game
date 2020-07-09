package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.game.graphics.SpriteSheet;
import de.game.utils.AABB;
import de.game.utils.Camera;
import de.game.utils.Vector2f;

public class Enemy extends Entity {

	private AABB sense;
	private int radius;

	protected int r_sense;

	protected AABB attackrange;
	protected int r_attackrange;

	private Camera camera;

	public Enemy(SpriteSheet sprite, Vector2f origin, int size, Camera camera) {
		super(sprite, origin, size);
		

		this.camera = camera;

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

		attackrange = new AABB(
				new Vector2f(origin.getX() + bounds.getxOffset() + bounds.getWidth() / 2 - r_attackrange / 2,
						origin.getY() + bounds.getyOffset() + bounds.getHeight() / 2 - r_attackrange / 2),
				r_attackrange);
	}

	public void update(Player player) {
		if (camera.getBounds().collides(this.bounds)) {
			chase(player);
			move();
			if (!tc.tileCollision(dx, 0)) {
				sense.getPos().setX((sense.getPos().getX() + dx));
				pos.setX(pos.getX() + dx);

			}
			if (!tc.tileCollision(0, dy)) {
				sense.getPos().setY((sense.getPos().getY() + dy));
				pos.setY(pos.getY() + dy);

			}
		}

	}

	@Override
	public void render(Graphics2D g) {
		if (camera.getBounds().collides(this.bounds)) {
			g.setColor(Color.GRAY);
			g.drawRect((int) (pos.getWorldVar().getX() + bounds.getxOffset()),
					(int) (pos.getWorldVar().getY() + bounds.getyOffset()), (int) bounds.getWidth(),
					(int) bounds.getHeight());

			g.setColor(Color.WHITE);
			g.drawOval((int) sense.getPos().getWorldVar().getX(), (int) sense.getPos().getWorldVar().getY(), radius,
					radius);
			g.drawImage(ani.getImage().image, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size,
					null);

			g.setColor(Color.red);
			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getxOffset() + 10),
					(int) (pos.getWorldVar().getY() - 5), 24, 5);

			g.setColor(Color.green);
			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getxOffset() + 10),
					(int) (pos.getWorldVar().getY() - 5), (int) (24 * healthpercent), 5);
		}
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
