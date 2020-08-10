package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.entity.Enemy;
import de.game.entity.Player;
import de.game.entity.enemies.Girl;
import de.game.graphics.Font;
import de.game.graphics.SpriteSheet;
import de.game.tiles.TileManager;
import de.game.utils.AABBTree;
import de.game.utils.Camera;
import de.game.utils.GameObjectHeap;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PlayState extends GameState {

	private Font font;
	private Player player;
	private TileManager tileManager;
	private Camera camera;
	private GameObjectHeap gameObjectHeap;
	private AABBTree aabbTree;
	private double heaptime;

	public static Vector2f map;

	public PlayState(GameStateManager gsm, Camera camera) {
		super(gsm);

		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());

		this.camera = camera;
		tileManager = new TileManager("tile/test.xml", camera);
		font = new Font("font/font.png", 10, 10);

		gameObjectHeap = new GameObjectHeap();
		aabbTree = new AABBTree();

		player = new Player(new SpriteSheet("entity/link.png", 32, 32),
				new Vector2f(GamePanel.WIDTH / 2 - 48, GamePanel.HEIGHT / 2 - 16), 64, tileManager);
		aabbTree.insert(player);
		for (int i = 0; i < 1; i++) {
			Enemy enemy = new Girl(new SpriteSheet("entity/enemy.png", 48, 48), new Vector2f(400, 40 * i + 20), 64,
					camera);

			gameObjectHeap.add(enemy.getBounds().distance(player.getPos()), enemy);
			aabbTree.insert(enemy);
		}
		camera.target(player);
	}

	@Override
	public void update(double time) {
		Vector2f.setWorldVar(map.getX(), map.getY());
		if (!gsm.isStateActive(GameStateManager.PAUSE)) {

			aabbTree.update(player);

			if (player.getDeath()) {
				gsm.add(GameStateManager.GAMEOVER);
//				gsm.remove(GameStateManager.PLAY);
			}

			for (int i = 0; i < gameObjectHeap.size(); i++) {
				if (gameObjectHeap.get(i).go instanceof Enemy) {
					Enemy enemy = ((Enemy) gameObjectHeap.get(i).go);
					if (player.getHitBounds().collides(enemy.getBounds())) {
						player.setTargetEnemy(enemy);
					}else {
						player.removeTargetEnemy(enemy);
					}

					if (enemy.getDeath()) {
						gameObjectHeap.remove(enemy);
					} else {
						enemy.update(player, time);
					}

					if (canBuildHeap(2500, 1000000, time)) {
						gameObjectHeap.get(i).value = enemy.getBounds().distance(player.getPos());
					}

					continue;
				}

			}

			player.update(time);
			camera.update();

		}
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {

		key.escape.tick();

		if (!gsm.isStateActive(GameStateManager.PAUSE)) {
			if (camera.getTarget() == player) {
				player.input(mouse, key);
			}
			camera.input(mouse, key);
		}

		if (key.escape.clicked) {
			if (gsm.isStateActive(GameStateManager.PAUSE)) {
				gsm.remove(GameStateManager.PAUSE);
			} else {
				gsm.add(GameStateManager.PAUSE);
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		tileManager.render(g);
		player.render(g);
		for (int i = 0; i < gameObjectHeap.size(); i++) {
			if (camera.getBounds().collides(gameObjectHeap.get(i).getBounds())) {
				gameObjectHeap.get(i).go.render(g);
			}
		}
		camera.render(g);
		SpriteSheet.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.WIDTH - 180, 40), 25,
				25, 25, 0);
	}

	private boolean canBuildHeap(int offset, int si, double time) {

		if (gameObjectHeap.size() > 3 && (heaptime / si) + offset < (time / si)) {
			return true;
		}

		return false;
	}

	public GameObjectHeap getGameObjectHeap() {
		return gameObjectHeap;
	}

	public AABBTree getAABBObjects() {
		return aabbTree;
	}

}
