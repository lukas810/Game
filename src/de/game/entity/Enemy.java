//package de.game.entity;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.util.ArrayList;
//
//import de.game.graphics.SpriteSheet;
//import de.game.utils.AABB;
//import de.game.utils.Camera;
//import de.game.utils.Vector2;
//
//public class Enemy extends Entity {
//
//	protected AABB sense;
//	protected int r_sense;
//
//	protected AABB attackrange;
//	protected int r_attackrange;
//
//	private Camera camera;
//
//	protected int xOffset;
//	protected int yOffset;
//
//	protected ArrayList<GameObject> collisions;
//	
//	public Enemy(SpriteSheet sprite, Vector2 origin, int size, Camera camera) {
//		super(sprite, origin, size);
//		this.camera = camera;
//		
//
//		sense = new AABB(new Vector2(origin.getX() + size / 2 - r_sense / 2, origin.getY() + size / 2 - r_sense / 2),
//				r_sense);
//		attackrange = new AABB(
//				new Vector2(origin.getX() + bounds.getxOffset() + bounds.getWidth() / 2 - r_attackrange / 2,
//						origin.getY() + bounds.getyOffset() + bounds.getHeight() / 2 - r_attackrange / 2),
//				r_attackrange);
//	}
//
//	public void chase(Player player) {
//		AABB playerBounds = player.getBounds();
//		if (sense.collisionCircleBox(playerBounds) && !attackrange.collisionCircleBox(playerBounds)) {
//			if (pos.getY() > player.pos.getY() + 1) {
//				up = true;
//			} else {
//				up = false;
//			}
//			if (pos.getY() < player.pos.getY() - 1) {
//				down = true;
//			} else {
//				down = false;
//			}
//
//			if (pos.getX() > player.pos.getX() + 1) {
//				left = true;
//			} else {
//				left = false;
//			}
//			if (pos.getX() < player.pos.getX() - 1) {
//				right = true;
//			} else {
//				right = false;
//			}
//		} else {
//			up = false;
//			down = false;
//			left = false;
//			right = false;
//		}
//	}
//
//	public void update(Player player, double time) {
//		if (camera.getBounds().collides(this.bounds)) {
//			super.update(time);
//			chase(player);
//			move();
//
//			bounds.setWidth(size / 2);
//			bounds.setHeight(size / 2 - yOffset);
//			bounds.setxOffset(size / 2 - xOffset - 15);
//			bounds.setyOffset(size / 2 + yOffset);
//
//			sense = new AABB(new Vector2(pos.getX() + size / 2 - r_sense / 2, pos.getY() + size / 2 - r_sense / 2),
//					r_sense);
//			attackrange = new AABB(
//					new Vector2(pos.getX() + bounds.getxOffset() + bounds.getWidth() / 2 - r_attackrange / 2,
//							pos.getY() + bounds.getyOffset() + bounds.getHeight() / 2 - r_attackrange / 2),
//					r_attackrange);
//
//			if (attackrange.collisionCircleBox(player.getBounds()) && !isInvincible) {
//				attack = true;
//				player.setHealth(player.getHealth() - damage, 10f * getDirection(),
//						currentDirection == UP || currentDirection == DOWN);
//			} else {
//				attack = false;
//			}
//
//			if (!tc.tileCollision(dx, 0)) {
//				sense.getPos().setX(sense.getPos().getX() + dx);
//				attackrange.getPos().setX(attackrange.getPos().getX() + dx);
//				pos.setX(pos.getX() + dx);
//			}
//			if (!tc.tileCollision(0, dy)) {
//				sense.getPos().setY(sense.getPos().getY() + dy);
//				attackrange.getPos().setY(attackrange.getPos().getY() + dy);
//				pos.setY(pos.getY() + dy);
//			}
//		}
//	}
//
//	@Override
//	public void render(Graphics2D g) {
//		if (camera.getBounds().collides(this.bounds)) {
//
//			g.setColor(Color.BLUE);
//			g.drawRect((int) (pos.getWorldVar().getX() + bounds.getxOffset()),
//					(int) (pos.getWorldVar().getY() + bounds.getyOffset()), (int) bounds.getWidth(),
//					(int) bounds.getHeight());
//
//			g.setColor(Color.RED);
//			g.drawRect((int) (hitBounds.getPos().getWorldVar().getX() + hitBounds.getxOffset()),
//					(int) (hitBounds.getPos().getWorldVar().getY() + hitBounds.getyOffset()),
//					(int) hitBounds.getWidth(), (int) hitBounds.getHeight());
//
//			// if(isInvincible)
//			if (useRight && left) {
//				g.drawImage(ani.getImage().image, (int) (pos.getWorldVar().getX()) + size,
//						(int) (pos.getWorldVar().getY()), -size, size, null);
//			} else {
//				g.drawImage(ani.getImage().image, (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()),
//						size, size, null);
//			}
//			
//			g.setColor(Color.WHITE);
//			g.drawOval((int)pos.getWorldVar().getX() + size / 2 - r_sense / 2,(int) pos.getWorldVar().getY() + size / 2 - r_sense / 2, r_sense, r_sense);
//
//			// Health Bar UI
//			g.setColor(Color.RED);
//			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getxOffset() + 5), (int) (pos.getWorldVar().getY()), 24,
//					5);
//
//			g.setColor(Color.GREEN);
//			g.fillRect((int) (pos.getWorldVar().getX() + bounds.getxOffset() + 5), (int) (pos.getWorldVar().getY()),
//					(int) (24 * healthpercent), 5);
//
//		}
//	}
//
//}
