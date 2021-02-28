//package de.game.utils;
//
//import de.game.GamePanel;
//import de.game.states.PlayState;
//import de.game.entity.Entity;
//import de.game.utils.Vector2;
//import de.game.utils.AABB;
//
//import java.awt.Graphics;
//
//public class Camera {
//
//	private AABB collisionCam;
//
//	private boolean up;
//	private boolean down;
//	private boolean left;
//	private boolean right;
//
//	private float dx;
//	private float dy;
//	private float maxSpeed = 8f;
//	private float acc = 3f;
//	private float deacc = 0.3f;
//
//	private int widthLimit;
//	private int heightLimit;
//
//	private int tileSize = 16;
//
//	private Entity e;
//
//	public Camera(AABB collisionCam) {
//		this.collisionCam = collisionCam;
//	}
//
//	public void setLimit(int widthLimit, int heightLimit) {
//		this.widthLimit = widthLimit;
//		this.heightLimit = heightLimit;
//	}
//
//	public void setTileSize(int tileSize) {
//		this.tileSize = tileSize;
//	}
//
//	public Entity getTarget() {
//		return e;
//	}
//
//	public Vector2 getPos() {
//		return collisionCam.getPos();
//	}
//
//	public AABB getBounds() {
//		return collisionCam;
//	}
//
//	public void update() {
//		move();
//		if (e != null) {
//			if (!e.xCol) {
//				if ((e.getPos().getWorldVar().getX() + dy) < Vector2
//						.getWorldVarX(widthLimit - collisionCam.getWidth() / 2) + tileSize
//						&& (e.getPos().getWorldVar().getX() + dy) > Vector2
//								.getWorldVarX(GamePanel.WIDTH / 2 - tileSize * 2)) {
//					PlayState.map.setX(PlayState.map.getX() + dx);
//					collisionCam.getPos().setX(collisionCam.getPos().getX() + dx);
//				}
//			}
//			if (!e.yCol) {
//				if ((e.getPos().getWorldVar().getY() + dy) < Vector2
//						.getWorldVarY(heightLimit - collisionCam.getHeight() / 2) + tileSize
//						&& (e.getPos().getWorldVar().getY() + dy) > Vector2
//								.getWorldVarY(GamePanel.HEIGHT / 2 - tileSize * 2)) {
//					PlayState.map.setY(PlayState.map.getY() + dy);
//					collisionCam.getPos().setY(collisionCam.getPos().getY() + dy);
//				}
//			}
//		} else {
//			if (collisionCam.getPos().getX() + dx > 0 && collisionCam.getPos().getWorldVar().getX()
//					+ dx < Vector2.getWorldVarX(widthLimit - collisionCam.getWidth()) - tileSize) {
//				PlayState.map.setX(PlayState.map.getX() + dx);
//				collisionCam.getPos().setX(collisionCam.getPos().getX() + dx);
//			}
//
//			if (collisionCam.getPos().getY() + dy > 0 && collisionCam.getPos().getWorldVar().getY()
//					+ dy < Vector2.getWorldVarY(heightLimit - collisionCam.getHeight()) - tileSize) {
//				PlayState.map.setY(PlayState.map.getY() + dy);
//				collisionCam.getPos().setY(collisionCam.getPos().getY() + dy);
//			}
//		}
//	}
//
//	private void move() {
//		if (up) {
//			dy -= acc;
//			if (dy < -maxSpeed) {
//				dy = -maxSpeed;
//			}
//		} else {
//			if (dy < 0) {
//				dy += deacc;
//				if (dy > 0) {
//					dy = 0;
//				}
//			}
//		}
//		if (down) {
//			dy += acc;
//			if (dy > maxSpeed) {
//				dy = maxSpeed;
//			}
//		} else {
//			if (dy > 0) {
//				dy -= deacc;
//				if (dy < 0) {
//					dy = 0;
//				}
//			}
//		}
//		if (left) {
//			dx -= acc;
//			if (dx < -maxSpeed) {
//				dx = -maxSpeed;
//			}
//		} else {
//			if (dx < 0) {
//				dx += deacc;
//				if (dx > 0) {
//					dx = 0;
//				}
//			}
//		}
//		if (right) {
//			dx += acc;
//			if (dx > maxSpeed) {
//				dx = maxSpeed;
//			}
//		} else {
//			if (dx > 0) {
//				dx -= deacc;
//				if (dx < 0) {
//					dx = 0;
//				}
//			}
//		}
//	}
//
//	public void target(Entity e) {
//		this.e = e;
//		if (e != null) {
//			acc = e.getAcc();
//			deacc = e.getDeacc();
//			maxSpeed = e.getMaxSpeed();
//		} else {
//			acc = 3;
//			deacc = 0.3f;
//			maxSpeed = 8;
//		}
//	}
//
//	public void setMaxSpeed(int maxSpeed) {
//		this.maxSpeed = maxSpeed;
//	}
//
//	public void input(MouseHandler mouse, KeyHandler key) {
//
//		if (e == null) {
//			if (key.up.down) {
//				up = true;
//			} else {
//				up = false;
//			}
//			if (key.down.down) {
//				down = true;
//			} else {
//				down = false;
//			}
//			if (key.left.down) {
//				left = true;
//			} else {
//				left = false;
//			}
//			if (key.right.down) {
//				right = true;
//			} else {
//				right = false;
//			}
//		} else {
//			if (!e.yCol) {
//				if (collisionCam.getPos().getY() + collisionCam.getHeight() / 2 + dy > e.getPos().getY()
//						+ e.getSize() / 2 + e.getDy() + 2) {
//					up = true;
//					down = false;
//				} else if (collisionCam.getPos().getY() + collisionCam.getHeight() / 2 + dy < e.getPos().getY()
//						+ e.getSize() / 2 + e.getDy() - 2) {
//					down = true;
//					up = false;
//				} else {
//					dy = 0;
//					up = false;
//					down = false;
//				}
//			}
//
//			if (!e.xCol) {
//				if (collisionCam.getPos().getX() + collisionCam.getWidth() / 2 + dx > e.getPos().getX()
//						+ e.getSize() / 2 + e.getDx() + 2) {
//					left = true;
//					right = false;
//				} else if (collisionCam.getPos().getX() + collisionCam.getWidth() / 2 + dx < e.getPos().getX()
//						+ e.getSize() / 2 + e.getDx() - 2) {
//					right = true;
//					left = false;
//				} else {
//					dx = 0;
//					right = false;
//					left = false;
//				}
//			}
//		}
//	}
//
//	public void render(Graphics g) {
//		/*
//		 * g.setColor(Color.blue); g.drawRect((int)
//		 * collisionCam.getPos().getWorldVar().x, (int)
//		 * collisionCam.getPos().getWorldVar().y, (int) collisionCam.getWidth(), (int)
//		 * collisionCam.getHeight()); g.setColor(Color.magenta);
//		 * g.drawLine(GamePanel.width / 2, 0, GamePanel.width / 2, GamePanel.height);
//		 * g.drawLine(0, GamePanel.height / 2, GamePanel.width,GamePanel.height / 2);
//		 */
//
//	}
//}