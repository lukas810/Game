package de.game.states;

import java.awt.Graphics2D;

import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;

public abstract class GameState {

	private GameStateManager gsm;

	public GameState(GameStateManager gsm) {
		this.setGsm(gsm);
	}
	
	public abstract void update();
	
	public abstract void input(MouseHandler mouse, KeyHandler key);
	
	public abstract void render(Graphics2D g);

	public GameStateManager getGsm() {
		return gsm;
	}

	public void setGsm(GameStateManager gsm) {
		this.gsm = gsm;
	}
}
