package de.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.game.graphics.Animation;
import de.game.graphics.Sprite;
import de.game.utils.AABB;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.TileCollision;
import de.game.utils.Vector2f;

public abstract class Entity {

	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int DOWN = 2;
	private static final int UP = 3;
//	private static final int FALLING = 4; unused
	private static final int ATTACK = 5;
	private static final int IDLE = 6;

	protected int currentDirection = DOWN;

	protected Animation ani;
	protected Sprite sprite;
	protected Vector2f pos;
	protected int size;
	protected int currentAnimation;

	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;
	protected boolean attack;
	protected boolean hasIdle = false;
	
	protected double attacktime;
	protected boolean canAttack = true;
	protected int attackSpeed = 1050;
    protected int attackDuration = 650;
    protected boolean attacking = false;

	protected float dx;
	protected float dy;
	protected float maxSpeed;
	protected float acc;
	protected float deacc;

	protected AABB hitBounds;
	protected AABB bounds;

	public boolean xCol = false;
	public boolean yCol = false;

	protected TileCollision tileCollision;

	public Entity(Sprite sprite, Vector2f origin, int size) {
		this.sprite = sprite;
		pos = origin;
		this.size = size;

		bounds = new AABB(origin, size, size);
		hitBounds = new AABB(origin, size, size);
		hitBounds.setxOffset(size / 2);

		ani = new Animation();
		setAnimation(DOWN, sprite.getSpriteArrayAtIndex(DOWN), 10);

		tileCollision = new TileCollision(this);
	}

	public abstract void render(Graphics2D g);

	public void input(MouseHandler mouse, KeyHandler key) {

	}

	public void setAnimation(int i, BufferedImage[] frames, int delay) {
		currentAnimation = i;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}

	public void update() {
		animate();
		setHitBoxDirection();
		ani.update();
	}

    public void animate() {

        if(attacking) {
            if(currentAnimation < 5) {
                setAnimation(currentAnimation + ATTACK, sprite.getSpriteArrayAtIndex(currentAnimation + ATTACK), attackDuration / 100);
            }
        } else if (up) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArrayAtIndex(UP), 5);
            }
        } else if (down) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArrayAtIndex(DOWN), 5);
            }
        } else if (left) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArrayAtIndex(LEFT), 5);
            }
        } else if (right) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArrayAtIndex(RIGHT), 5);
            }
        } else {
            if(!attacking && currentAnimation > 4) {
                setAnimation(currentAnimation - ATTACK, sprite.getSpriteArrayAtIndex(currentAnimation - ATTACK), -1);
            } else if(!attacking) {
                if(hasIdle && currentAnimation != IDLE) {
                    setAnimation(IDLE, sprite.getSpriteArrayAtIndex(IDLE), 10);
                } else if(!hasIdle) {
                    setAnimation(currentAnimation, sprite.getSpriteArrayAtIndex(currentAnimation), -1);
                }
            }
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
	}

    private void setHitBoxDirection() {
        if (up && !attacking) {
            hitBounds.setxOffset((size - hitBounds.getWidth()) / 2);
            hitBounds.setyOffset(-hitBounds.getHeight() / 2 + hitBounds.getxOffset());
        } else if (down && !attacking) {
            hitBounds.setxOffset((size - hitBounds.getWidth()) / 2);
            hitBounds.setyOffset(hitBounds.getHeight() / 2 + hitBounds.getxOffset());
        } else if (left && !attacking) {
            hitBounds.setyOffset((size - hitBounds.getHeight()) / 2);
            hitBounds.setxOffset(-hitBounds.getWidth() / 2 + hitBounds.getyOffset()); 
        } else if (right && !attacking) {
            hitBounds.setyOffset((size - hitBounds.getHeight()) / 2);
            hitBounds.setxOffset(hitBounds.getWidth() / 2 + hitBounds.getyOffset());
        }
    }
    
    protected boolean isAttacking(double time) {

        if((attacktime / 1000000) > ((time / 1000000) - attackSpeed)) {
            canAttack = false;
        } else {
            canAttack = true;
        }

        if((attacktime / 1000000) + attackDuration > (time / 1000000)) {
            return true;
        }

        return false;
    }

	public int getSize() {
		return size;
	}

	public Animation getAnimation() {
		return ani;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public AABB getBounds() {
		return bounds;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setAcc(float acc) {
		this.acc = acc;
	}

	public void setDeacc(float deacc) {
		this.deacc = deacc;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public float getAcc() {
		return acc;
	}

	public float getDeacc() {
		return deacc;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

}
