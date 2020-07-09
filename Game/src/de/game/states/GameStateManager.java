package de.game.states;

import java.awt.Graphics2D;

import de.game.GamePanel;
import de.game.utils.AABB;
import de.game.utils.Camera;
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
	
	public static Camera camera;
    public static Graphics2D g;


	public GameStateManager(Graphics2D g) {
		GameStateManager.g = g;
		map = new Vector2f(GamePanel.WIDTH, GamePanel.HEIGHT);
		Vector2f.setWorldVar(map.getX(), map.getY());
		states = new GameState[4];
		
		camera = new Camera(new AABB(new Vector2f(-32, -32), GamePanel.WIDTH + 64, GamePanel.HEIGHT + 64));

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
			camera = new Camera(new AABB(new Vector2f(-64, -64), GamePanel.WIDTH + 128, GamePanel.HEIGHT + 128));
			states[PLAY] = new PlayState(this, camera);
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
