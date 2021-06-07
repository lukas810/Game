package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import org.jbox2d.dynamics.World;

import de.game.graphics.SpriteSheet;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2;
import de.game.jbox2d.JBox2DHelper;

public class Player extends Entity {

	public Player(Vector2 pos, float width, float height, SpriteSheet spriteSheet, World world) {
		super(pos, width, height, spriteSheet, world);

		maxSpeed = 100f;
		acc = 5f;
		deacc = 10f;

		ani.setNumFrames(8, RIGHT);
		ani.setNumFrames(8, LEFT);
		ani.setNumFrames(8, UP);
		ani.setNumFrames(8, DOWN);

	}

	@Override
	public void update(double time) {
		super.update(time);
		move();
	}

	@Override
	public void render(Graphics2D g) {
		Vector2 pos = JBox2DHelper.coordsWorldToPixel(body.getPosition().x, body.getPosition().y);
		AffineTransform transform = new AffineTransform();
		transform.translate((int) (pos.getX()), (int) (pos.getY()));
		g.transform(transform);
		g.drawImage(ani.getImage().image, (int) -width / 2, (int) -height / 2, (int) width, (int) height, null);
		g.setColor(Color.BLUE);
		g.drawRect((int) -width / 2, (int) -height / 2, (int) width, (int) height);

		try {
			g.transform(transform.createInverse());
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
	}

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

		// moonwalking
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