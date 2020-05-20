package de.game.states;

import java.awt.Color;
import java.awt.Graphics2D;

import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;

public class PlayState extends GameState {

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void update() {

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
	
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(100, 100, 32, 32);
	}

}
