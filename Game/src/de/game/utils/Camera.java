package de.game.utils;

import java.awt.Color;
import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.entity.Entity;
import de.game.states.PlayState;

public class Camera {

	private AABB collisionCam;

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	private float dx;
	private float dy;
	private float maxSpeed = 2.5f;
	private float acc = 0.7f;
	private float deacc = 0.7f;

	private int widthLimit;
	private int heightLimit;

	private int tileSize = 16;

	private Entity entity;

	public Camera(AABB collisionCam) {
		this.collisionCam = collisionCam;
	}

	public void setLimit(int widthLimit, int heightLimit) {
		this.widthLimit = widthLimit;
		this.heightLimit = heightLimit;
	}

	public void update() {
		move();
		if (entity != null) {
			if (!entity.xCol) {
				if ((entity.getBounds().getPos().getWorldVar().getX() + dy) < Vector2f
						.getWorldVarX(widthLimit - collisionCam.getWidth() / 2) + tileSize
						&& (entity.getBounds().getPos().getWorldVar().getX() + dy) > Vector2f
								.getWorldVarX(GamePanel.WIDTH / 2 - tileSize * 2)) {

					PlayState.map.setX(PlayState.map.getX() + dx);
					collisionCam.getPos().setX(collisionCam.getPos().getX() + dx);

				}
			}
			if (!entity.yCol) {
				if ((entity.getBounds().getPos().getWorldVar().getY() + dy) < Vector2f
						.getWorldVarY(heightLimit - collisionCam.getHeight() / 2) + tileSize
						&& (entity.getBounds().getPos().getWorldVar().getY() + dy) > Vector2f
								.getWorldVarY(GamePanel.HEIGHT / 2 - tileSize * 2)) {
					PlayState.map.setY(PlayState.map.getY() + dy);
					collisionCam.getPos().setY(collisionCam.getPos().getY() + dy);

				}

			}
		} else {
			if (collisionCam.getPos().getX() + dx > 0 && collisionCam.getPos().getWorldVar().getX()
					+ dx < Vector2f.getWorldVarX(widthLimit - collisionCam.getWidth()) - tileSize) {
				PlayState.map.setX(PlayState.map.getX() + dx);
				collisionCam.getPos().setX(collisionCam.getPos().getX() + dx);
			}

			if (collisionCam.getPos().getY() + dy > 0 && collisionCam.getPos().getWorldVar().getY()
					+ dy < Vector2f.getWorldVarY(heightLimit - collisionCam.getHeight()) - tileSize) {
				PlayState.map.setY(PlayState.map.getY() + dy);
				collisionCam.getPos().setY(collisionCam.getPos().getY() + dy);
			}
		}

	}

	private void move() {
		if (up) {
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
	}

	public void target(Entity entity) {
		this.entity = entity;
		deacc = entity.getDeacc();
		maxSpeed = entity.getMaxSpeed();
	}

	public void input(KeyHandler key, MouseHandler mouse) {
		if (entity == null) {

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

		} else {
			if (!entity.yCol) {
				if (collisionCam.getPos().getY() + collisionCam.getHeight() / 2
						+ dy > entity.getBounds().getPos().getY() + entity.getSize() / 2 + entity.getDy() + 2) {
					up = true;
					down = false;
				} else if (collisionCam.getPos().getY() + collisionCam.getHeight() / 2
						+ dy < entity.getBounds().getPos().getY() + entity.getSize() / 2 + entity.getDy() - 2) {
					down = true;
					up = false;
				} else {
					dy = 0;
					up = false;
					down = false;
				}
			}

			if (!entity.xCol) {
				if (collisionCam.getPos().getX() + collisionCam.getWidth() / 2 + dx > entity.getBounds().getPos().getX()
						+ entity.getSize() / 2 + entity.getDx() + 2) {
					left = true;
					right = false;
				} else if (collisionCam.getPos().getX() + collisionCam.getWidth() / 2
						+ dx < entity.getBounds().getPos().getX() + entity.getSize() / 2 + entity.getDx() - 2) {
					right = true;
					left = false;
				} else {
					dx = 0;
					right = false;
					left = false;
				}

			}
		}
	}

	public void render(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.drawRect((int) collisionCam.getPos().getWorldVar().getX(), (int) collisionCam.getPos().getWorldVar().getY(),
				(int) collisionCam.getWidth(), (int) collisionCam.getHeight());
	}

	public AABB getBounds() {
		return collisionCam;
	}

	public int getWidthLimit() {
		return widthLimit;
	}

	public void setWidthLimit(int widthLimit) {
		this.widthLimit = widthLimit;
	}

	public int getHeightLimit() {
		return heightLimit;
	}

	public void setHeightLimit(int heightLimit) {
		this.heightLimit = heightLimit;
	}
	
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	
	public Vector2f getPos() {
        return collisionCam.getPos();
    }

}
