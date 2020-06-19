package de.game;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{

	private BufferStrategy bs;
    private GamePanel gamepanel;

    public Window() {
        setTitle("Decay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();

        createBufferStrategy(2);
        bs = getBufferStrategy();

        gamepanel = new GamePanel(bs);
        setContentPane(gamepanel);
        
    }
}
