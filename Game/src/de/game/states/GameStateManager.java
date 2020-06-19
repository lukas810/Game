package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
//import de.game.graphics.Font;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class GameStateManager {

	private GameState[] states;

	public static Vector2f map;

	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;


	public GameStateManager() {
		map = new Vector2f(GamePanel.WIDTH, GamePanel.HEIGHT);
		Vector2f.setWorldVar(map.getX(), map.getY());
		states = new GameState[4];

		add(PLAY);
	}

	public void remove(int state) {
		states[state] = null;
	}

	public void add(int state) {
		if (states[state] != null) {
			return;
		}
		if (state == PLAY) {
			states[PLAY] = new PlayState(this);
		}
		if (state == MENU) {
			states[MENU] = new MenuState(this);
		}
		if (state == PAUSE) {
			states[PAUSE] = new PauseState(this);
		}
		if (state == GAMEOVER) {
			states[GAMEOVER] = new GameOverState(this);
		}
	}
	
	  public boolean isStateActive(int state) {
	        return states[state] != null;
	    }

	public void addAndRemove(int state) {
		addAndRemove(state, 0);
	}

	public void addAndRemove(int state, int remove) {
		remove(state);
		add(state);
	}

	public void update(double time) {
		for (int i = 0; i < states.length; i++) {
			if (states[i] != null) {
				states[i].update(time);
			}
		}
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		for (int i = 0; i < states.length; i++) {
			if (states[i] != null) {
				states[i].input(mouse, key);
			}
		}
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < states.length; i++) {
			if (states[i] != null) {
				states[i].render(g);
			}
		}
	}
	
	public boolean getState(int state) {
		return states[state] != null;
	}
}
