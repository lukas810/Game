package de.game.entity.test;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import de.game.jbox2d.JBox2DHelper;
import de.game.utils.Vector2;

public class Boundary {

		private Body body;

		private float w;
		private float h;
		private Color color;

		public Boundary(float x, float y, float w, float h, Color color, World world) {
			this.w = w;
			this.h = h;
			this.color = color;
			
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyType.STATIC;
			bodyDef.position.set(JBox2DHelper.coordsPixelToWorld(x, y));
			body = world.createBody(bodyDef);
			PolygonShape boundary = new PolygonShape();
			float box2dWidth = JBox2DHelper.scalarPixelsToWorld(w /2);
			float box2dHeight = JBox2DHelper.scalarPixelsToWorld(h / 2);
			boundary.setAsBox(box2dWidth, box2dHeight);

			body.createFixture(boundary, 0);
			body.setUserData(this);
		}

		public void update() {
			Vector2 pos = JBox2DHelper.coordsWorldToPixel(body.getPosition().x, body.getPosition().y);
			System.out.println(pos.getY());
		}

		public void render(Graphics2D g2d) {
			Vector2 pos = JBox2DHelper.coordsWorldToPixel(body.getPosition().x, body.getPosition().y);
			g2d.setColor(color);
			g2d.fillRect((int) (pos.getX() - w / 2), (int) (pos.getY() - h / 2), (int) w, (int) h);
		}
	}