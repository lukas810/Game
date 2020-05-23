package de.game.states;

import java.awt.Graphics2D;

import de.game.graphics.Font;
import de.game.graphics.Sprite;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PlayState extends GameState {

	private Font font;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		font = new Font("font/TestFont.png", 16, 16);
	}

	@Override
	public void update() {

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {

	}

	@Override
	public void render(Graphics2D g) {
		Sprite.drawArray(g, font, "Lost", new Vector2f(100, 100), 32, 32, 16, 0);
	}

}
