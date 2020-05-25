package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.entity.Player;
import de.game.graphics.Font;
import de.game.graphics.Sprite;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PlayState extends GameState {

	private Font font;
	private Player player;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		font = new Font("font/font.png", 10, 10);
		player = new Player(new Sprite("entity/link.png"), new Vector2f(350, 250), 48);
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
		player.render(g);
		Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.WIDTH - 180, 40), 25, 25, 25,
				0);
	}

}
