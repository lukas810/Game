package de.game;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{

	public Window() {
		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(new GamePanel());
		setIgnoreRepaint(true);
		pack();	
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
