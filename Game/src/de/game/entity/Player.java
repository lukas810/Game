package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.game.graphics.Sprite;
import de.game.states.PlayState;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class Player extends Entity {

	public Player(Sprite sprite, Vector2f origin, int size) {
		super(sprite, origin, size);
		
		maxSpeed = 2.5f;
		acc = 0.7f;
		deacc = 0.7f;
		bounds.setWidth(40);
		bounds.setHeight(25);
		bounds.setxOffset(15);
		bounds.setyOffset(35);

	}

	@Override
	public void update() {
		super.update();
		move();
		if (!tileCollision.collisionTile(dx, 0)) {
			PlayState.map.setX(PlayState.map.getX() + dx);
			pos.setX(pos.getX() + dx);

		}
		if (!tileCollision.collisionTile(0, dy)) {
			PlayState.map.setY(PlayState.map.getY() + dy);
			pos.setY(pos.getY() + dy);
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getxOffset()),
				(int) (pos.getWorldVar().getY() + bounds.getyOffset()), (int) bounds.getWidth(),
				(int) bounds.getHeight());
		g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
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

}
