package de.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import de.game.GamePanel;
import de.game.graphics.SpriteSheet;
import de.game.tiles.TileManager;
import de.game.tiles.blocks.NormBlock;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;
import de.game.graphics.Sprite;;

public class Player extends Entity {

	private ArrayList<Enemy> enemy;
	private ArrayList<GameObject> go;
	private TileManager tm;

	public Player(SpriteSheet sprite, Vector2f origin, int size, TileManager tm) {
		super(sprite, origin, size);
		this.tm = tm;

		maxSpeed = 2.5f;
		acc = 0.7f;
		deacc = 0.7f;

		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setxOffset(12);
		bounds.setyOffset(40);

		hitBounds.setWidth(42);
		hitBounds.setHeight(42);

		ani.setNumFrames(8, RIGHT);
		ani.setNumFrames(8, LEFT);
		ani.setNumFrames(8, UP);
		ani.setNumFrames(8, DOWN);
		ani.setNumFrames(8, ATTACK + RIGHT);
		ani.setNumFrames(8, ATTACK + LEFT);
		ani.setNumFrames(8, ATTACK + UP);
		ani.setNumFrames(8, ATTACK + DOWN);

		enemy = new ArrayList<Enemy>();
		go = new ArrayList<GameObject>();

		hasIdle = false;
		health = 100;
		maxHealth = 100;
		damage = 10;

	}
	
	public void setTargetEnemy(Enemy enemy) { 
        this.enemy.add(enemy);
    }
	
	public void removeTargetEnemy(Enemy enemy) {
		this.enemy.remove(enemy);
	}

    public void setTargetGameObject(GameObject go) {
        if(!this.go.contains(go))
            this.go.add(go);
    }

    @Override
	public void update(double time) {
		super.update(time);
		attacking = isAttacking(time);
		for (int i = 0; i < enemy.size(); i++) {
			if (attacking) {
				enemy.get(i).setHealth(enemy.get(i).getHealth() - damage, force * getDirection(),
						currentDirection == UP || currentDirection == DOWN);
				if(enemy.get(i).getHealth() <= 0) {
					enemy.remove(i);
				}
			}
		}

		move();
		if (!tc.tileCollision(dx, 0) && !bounds.collides(dx, 0, go)) {

			pos.setX(pos.getX() + dx);
			xCol = false;

		} else {
			xCol = true;
		}
		if (!tc.tileCollision(0, dy) && !bounds.collides(0, dy, go)) {
			pos.setY(pos.getY() + dy);
			yCol = false;
		} else {
			yCol = true;
		}
		
		tc.normalTile(dx, 0);
        tc.normalTile(0, dy);
        
        NormBlock[] block = tm.getNormalTile(tc.getTile());
        for(int i = 0; i < block.length; i++) {
            if(block[i] != null)
                block[i].getImage().restoreDefault();
        }
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) (pos.getWorldVar().getX() + bounds.getxOffset()),
				(int) (pos.getWorldVar().getY() + bounds.getyOffset()), (int) bounds.getWidth(),
				(int) bounds.getHeight());

		if (attack) {
			g.setColor(Color.RED);
			g.drawRect((int) (hitBounds.getPos().getWorldVar().getX() + hitBounds.getxOffset()),
					(int) (hitBounds.getPos().getWorldVar().getY() + hitBounds.getyOffset()),
					(int) hitBounds.getWidth(), (int) hitBounds.getHeight());
		}
		
		if(isInvincible) {
            if(GamePanel.tickCount % 30 >= 15) {
                ani.getImage().setEffect(Sprite.effect.REDISH);
            } else {
                ani.getImage().restoreColors();
            }
        } else {
            ani.getImage().restoreColors();
        }
		if(useRight && left) {
            g.drawImage(ani.getImage().image, (int) (pos.getWorldVar().getX()) + size, (int) (pos.getWorldVar().getY()), -size, size, null);
        } else {
            g.drawImage(ani.getImage().image, (int) (pos.getWorldVar().getX()), (int) (pos.getWorldVar().getY()), size, size, null);
        }
		
		g.setColor(Color.red);
		g.fillRect((int) (pos.getWorldVar().getX() + bounds.getxOffset() +5), (int) (pos.getWorldVar().getY()),
				24, 5);

		g.setColor(Color.green);
		g.fillRect((int) (pos.getWorldVar().getX() + bounds.getxOffset() +5), (int) (pos.getWorldVar().getY()),
				(int) (24 * healthpercent), 5);
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
		if (key.attack.down && canAttack) {
			attack = true;
			attacktime = System.nanoTime();
		} else {
			if (!attacking) {
				attack = false;
			}
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
