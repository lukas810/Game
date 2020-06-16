package de.game.states;

import java.awt.Graphics2D;
import java.util.ArrayList;

import de.game.GamePanel;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class GameStateManager {

	private ArrayList<GameState> states;
	
	public static Vector2f map;
	
	public static final int PLAY = 0;
	public static final int MENU = 0;
	public static final int PAUSE = 0;
	public static final int GAMEOVER = 0;
	
	public GameStateManager() {
		map = new Vector2f(GamePanel.WIDTH,GamePanel.HEIGHT);
		Vector2f.setWorldVar(map.getX(), map.getY());
		states = new ArrayList<GameState>();
		states.add(new PlayState(this));
	}
	
	public void remove(int state) {
		states.remove(state);
	}
	
	public void add(int state) {
		if(state == PLAY) {
			states.add(new PlayState(this));
		}
		if(state == MENU) {
			states.add(new MenuState(this));
		}
		if(state == PAUSE) {
			states.add(new PauseState(this));
		}
		if(state == GAMEOVER) {
			states.add(new GameOverState(this));
		}
	}
	
	public void addAndPop(int state) {
		states.remove(0);
		add(state);
		
	}
	
	public  void update() {
		Vector2f.setWorldVar(map.getX(), map.getY());
		for(int i = 0; i<states.size();i++) {
			states.get(i).update();
		}
	}
	
	public  void input(MouseHandler mouse, KeyHandler key) {
		key.escape.tick();
		for(int i = 0; i<states.size();i++) {
			states.get(i).input(mouse, key);
		}
		
		if(key.escape.clicked) {
			System.exit(0);
		}
	}
	
	public  void render(Graphics2D g2) {
		for(int i = 0; i<states.size();i++) {
			states.get(i).render(g2);
		}
	}
}
