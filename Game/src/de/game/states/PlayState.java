package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.entity.Enemy;
import de.game.entity.Player;
import de.game.graphics.Font;
import de.game.graphics.Sprite;
import de.game.tiles.TileManager;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PlayState extends GameState {

	private Font font;
	private Player player;
	private Enemy enemy;
	private TileManager tileManager;

	public static Vector2f map;

	public PlayState(GameStateManager gsm) {
		super(gsm);

		map = new Vector2f();
		Vector2f.setWorldVar(map.getX(), map.getY());
		tileManager = new TileManager("tile/test.xml");
		font = new Font("font/font.png", 10, 10);
		player = new Player(new Sprite("entity/link.png"),
				new Vector2f((GamePanel.WIDTH / 2) - 32, (GamePanel.HEIGHT / 2) - 32), 64);
		enemy = new Enemy(new Sprite("entity/enemy.png", 48, 48),
				new Vector2f(550,75), 64);
	}

	@Override
	public void update() {
		Vector2f.setWorldVar(map.getX(), map.getY());
		player.update(enemy);
		enemy.update(player);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
		tileManager.render(g);
		player.render(g);
		enemy.render(g);
		Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.WIDTH - 180, 40), 25, 25, 25,
				0);
	}

}
