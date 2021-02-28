package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import de.game.graphics.SpriteSheet;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2;
import de.game.jbox2d.JBox2DHelper;

public class Player extends Entity {

	private Body body;

	public Player(Vector2 pos, float width, float height, SpriteSheet spriteSheet, World world) {
		super(pos, width, height, spriteSheet);

		maxSpeed = 100f;
		acc = 2f;
		deacc = 10f;

		ani.setNumFrames(8, RIGHT);
		ani.setNumFrames(8, LEFT);
		ani.setNumFrames(8, UP);
		ani.setNumFrames(8, DOWN);
//		ani.setNumFrames(8, ATTACK + RIGHT);
//		ani.setNumFrames(8, ATTACK + LEFT);
//		ani.setNumFrames(8, ATTACK + UP);
//		ani.setNumFrames(8, ATTACK + DOWN);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(JBox2DHelper.coordsPixelToWorld(pos.x, pos.y));
		body = world.createBody(bodyDef);
		PolygonShape box = new PolygonShape();
		float box2dWidth = JBox2DHelper.scalarPixelsToWorld(width / 2);
		float box2dHeight = JBox2DHelper.scalarPixelsToWorld(height / 2);
		box.setAsBox(box2dWidth, box2dHeight);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = box;
		fixtureDef.density = 1;
		fixtureDef.friction = 0.3f;
		fixtureDef.restitution = 1;

		body.createFixture(fixtureDef);
//		body.setUserData(this);

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

	public void move() {
		if (up) {
			currentDirection = UP;
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
			currentDirection = DOWN;
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
		body.setLinearVelocity(JBox2DHelper.vectorPixelsToWorld(dx, dy));
	}

}
