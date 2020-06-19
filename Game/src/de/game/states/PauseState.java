package de.game.states;

import java.awt.Graphics2D;

import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;

public class PauseState extends GameState {

	public PauseState(GameStateManager gsm) {
		super(gsm);

	}

	@Override
	public void update(double time) {
		System.out.println("paused");
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		key.escape.tick();

		if (key.escape.clicked) {
			gsm.remove(GameStateManager.PAUSE);
		}

	}

	@Override
	public void render(Graphics2D g) {

	}

}
