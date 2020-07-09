package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.entity.Enemy;
import de.game.entity.Player;
import de.game.graphics.Font;
import de.game.graphics.SpriteSheet;
import de.game.tiles.TileManager;
import de.game.utils.Camera;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PlayState extends GameState {

	private Font font;
	private Player player;
//	private Enemy enemy;
	private TileManager tileManager;
	private Camera camera;

	public static Vector2f map;

	public PlayState(GameStateManager gsm, Camera camera) {
		super(gsm);

		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());

		this.camera = camera;
		tileManager = new TileManager("tile/test.xml", camera);
		font = new Font("font/font.png", 10, 10);
		player = new Player(new SpriteSheet("entity/link.png"),
				new Vector2f(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2), 64, tileManager);
//		enemy = new Enemy(new SpriteSheet("entity/enemy.png", 48, 48), new Vector2f(550, 75), 64, camera);

		camera.target(player);
	}

	@Override
	public void update(double time) {
		Vector2f.setWorldVar(map.getX(), map.getY());
		if (!gsm.isStateActive(GameStateManager.PAUSE)) {
			player.update(time);
//			enemy.update(player);
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
//		enemy.render(g);
		camera.render(g);
		SpriteSheet.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.WIDTH - 180, 40), 25,
				25, 25, 0);
	}

}
