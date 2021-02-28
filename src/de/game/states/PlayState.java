package de.game.states;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import de.game.GamePanel;
import de.game.entity.Boundary;
import de.game.entity.Player;
import de.game.graphics.Font;
import de.game.graphics.SpriteSheet;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2;

public class PlayState extends GameState {

	private World world;
	private float timeStep = 1.0f / 60.0f;
	private int velocityIterations = 8;
	private int positionIterations = 3;

	private Player player;
	
	private Boundary test;

	private Font font;

	public PlayState(GameStateManager gsm) {
		super(gsm);

		setupWorld();
		
		font = new Font("font/font.png", 10, 10);

		player = new Player(new Vector2(GamePanel.WIDTH / 2 - 48, GamePanel.HEIGHT / 2 - 16), 64, 64,
				new SpriteSheet("entity/link.png", 32, 32), world);
		
		test = new Boundary(300, 400, 400, 10, Color.BLUE, world);
	}


	@Override
	public void update(double time) {
		world.step(timeStep, velocityIterations, positionIterations);
		player.update(time);
	}

	@Override
	public void render(Graphics2D g) {
		SpriteSheet.drawArray(g, font, GamePanel.oldFrameCount + "FPS", new Vector2(GamePanel.WIDTH - 180, 40), 25, 25,
				25, 0);
		player.render(g);
		test.render(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}
	
	private void setupWorld() {
		world = new World(new Vec2(0, 0));
		world.setWarmStarting(true);
		world.setContinuousPhysics(true);
	}
}
