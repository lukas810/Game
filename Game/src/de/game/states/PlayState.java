package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.entity.Enemy;
import de.game.entity.Player;
import de.game.graphics.Font;
import de.game.graphics.Sprite;
import de.game.tiles.TileManager;
import de.game.utils.AABB;
import de.game.utils.Camera;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PlayState extends GameState {

	private Font font;
	private Player player;
	private Enemy enemy;
	private TileManager tileManager;
	private Camera camera;

	public static Vector2f map;

	public PlayState(GameStateManager gsm) {
		super(gsm);

		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());


		camera = new Camera(new AABB(new Vector2f(16, 16), GamePanel.WIDTH -16, GamePanel.HEIGHT -16));
		tileManager = new TileManager("tile/test.xml", camera);
		font = new Font("font/font.png", 10, 10);
		player = new Player(new Sprite("entity/link.png"),
				new Vector2f(0 + (GamePanel.WIDTH / 2) - 32, 0 + (GamePanel.HEIGHT / 2) - 32), 64);
		enemy = new Enemy(new Sprite("entity/enemy.png", 48, 48), new Vector2f(550, 75), 64);
		
		camera.target(player);
	}

	@Override
	public void update() {
		Vector2f.setWorldVar(map.getX(), map.getY());
		player.update(enemy);
		enemy.update(player);
		camera.update();
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(key, mouse);
		camera.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
		tileManager.render(g);
		player.render(g);
		enemy.render(g);
		camera.render(g);
		Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.WIDTH - 180, 40), 25, 25, 25,
				0);
	}

}
