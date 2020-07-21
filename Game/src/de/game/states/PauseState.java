package de.game.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.game.GamePanel;
import de.game.ui.Button;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class PauseState extends GameState {

	private Button buttonResume;
	private Button buttonExit;
	private Font font;

	public PauseState(GameStateManager gsm) {
		super(gsm);
		BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
		BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

		font = new Font("font", Font.PLAIN, 22);
		buttonResume = new Button("Resume", imgButton, font,
				new Vector2f(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 - 60), 16, 16);
		buttonResume.addHoverImage(
				buttonResume.createButton("Resume", imgHover, font, buttonResume.getWidth(), buttonResume.getHeight(), 16, 16));

		buttonResume.addEvent(e -> {
			gsm.remove(GameStateManager.PAUSE);
		});
		
		buttonExit = new Button("Exit", imgButton, font,
				new Vector2f(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 ), 58, 12);
		buttonExit.addHoverImage(
				buttonExit.createButton("Exit", imgHover, font, buttonExit.getWidth(), buttonExit.getHeight(), 58, 12));

	}

	@Override
	public void update(double time) {

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		buttonResume.input(mouse, key);
		buttonExit.input(mouse, key);

	}

	@Override
	public void render(Graphics2D g) {
		buttonResume.render(g);
		buttonExit.render(g);
	}

}
