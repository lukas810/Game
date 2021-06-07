package de.game.entity;

import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import de.game.graphics.Sprite;
import de.game.graphics.SpriteSheet;
import de.game.jbox2d.JBox2DHelper;
import de.game.utils.Vector2;

public class GameObject {

	protected Vector2 pos;
	protected Sprite sprite;
	protected SpriteSheet spriteSheet;
	
	protected float width;
	protected float height;
	
	protected Body body;
	
	//TODO vielleicht den BodyType übergeben damit kein 2ter konstruktor gebraucht wird
	public GameObject(Vector2 pos, float width, float height, SpriteSheet spriteSheet, World world) {
		this.pos = pos;
		this.spriteSheet = spriteSheet;
		this.width = width;
		this.height = height;
		createBody(BodyType.DYNAMIC, world);
	}

	public void render(Graphics2D g) {
		g.drawImage(sprite.image, (int) pos.x, (int) pos.y, (int) width, (int) height, null);
	}
	
	private void createBody(BodyType bodyType, World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
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
		
	}
	
}