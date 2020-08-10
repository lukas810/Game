package de.game.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.game.GamePanel;
import de.game.graphics.SpriteSheet;
import de.game.states.GameStateManager;
import de.game.utils.AABB;
import de.game.utils.KeyHandler;
import de.game.utils.MouseHandler;
import de.game.utils.Vector2f;

public class Button {

	private String label;
	private int lbWidth;
	private int lbHeight;

	private BufferedImage image;
	private BufferedImage hoverImage;
	private BufferedImage pressedImage;

	private Vector2f iPos;
	private Vector2f lbPos;

	private AABB bounds;
	private boolean hovering = false;
	private int hoverSize;
	private ArrayList<ClickedEvent> events;
	private ArrayList<SlotEvent> slotevents;
	private boolean clicked = false;
	private boolean pressed = false;
	private boolean canHover = true;
	private boolean drawString = true;

	private float pressedtime;
	private Slots slot;

	public Button(BufferedImage icon, BufferedImage image, Vector2f pos, int width, int height, int iconsize) {
		this.image = createIconButton(icon, image, width + iconsize, height + iconsize, iconsize);
		this.iPos = pos;
		this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());

		events = new ArrayList<ClickedEvent>();
		slotevents = new ArrayList<SlotEvent>();
		this.canHover = false;
		this.drawString = false;
	}

	public Button(String label, BufferedImage image, Font font, Vector2f pos, int buttonSize) {
		this(label, image, font, pos, buttonSize, -1);
	}

	public Button(String label, BufferedImage image, Font font, Vector2f pos, int buttonWidth, int buttonHeight) {
		GameStateManager.g.setFont(font);
		FontMetrics met = GameStateManager.g.getFontMetrics(font);
		int height = met.getHeight();
		int width = met.stringWidth(label);

		if (buttonWidth == -1)
			buttonWidth = buttonHeight;

		this.label = label;

		this.image = createButton(label, image, font, width + buttonWidth, height + buttonHeight, buttonWidth,
				buttonHeight);
		this.iPos = new Vector2f(pos.getX() - this.image.getWidth() / 2, pos.getY() - this.image.getHeight() / 2);
		this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());

		events = new ArrayList<ClickedEvent>();
		this.canHover = false;
		this.drawString = false;
	}

	private BufferedImage createIconButton(BufferedImage icon, BufferedImage image, int width, int height,
			int iconsize) {
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		if (image.getWidth() != width || image.getHeight() != height) {
			image = resizeImage(image, width, height);
		}

		if (icon.getWidth() != width - iconsize || icon.getHeight() != height - iconsize) {
			icon = resizeImage(icon, width - iconsize, height - iconsize);
		}

		Graphics g = result.getGraphics();
		g.drawImage(image, 0, 0, width, height, null);

		g.drawImage(icon, image.getWidth() / 2 - icon.getWidth() / 2, image.getHeight() / 2 - icon.getHeight() / 2,
				icon.getWidth(), icon.getHeight(), null);

		g.dispose();

		return result;
	}

	public BufferedImage createButton(String label, BufferedImage image, Font font, int width, int height,
			int buttonWidth, int buttonHeight) {
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		if (image.getWidth() != width || image.getHeight() != height) {
			image = resizeImage(image, width, height);
		}

		Graphics g = result.getGraphics();
		g.drawImage(image, 0, 0, width, height, null);

		g.setFont(font);
		g.drawString(label, buttonWidth / 2, (height - buttonHeight));

		g.dispose();

		return result;
	}

	private BufferedImage resizeImage(BufferedImage image, int width, int height) {
		Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();

		g.drawImage(temp, 0, 0, null);
		g.dispose();

		return result;
	}

	public Button(String label, int lbWidth, int lbHeight, BufferedImage image, int iWidth, int iHeight,
			Vector2f offset) {
		this(label, lbWidth, lbHeight, image, iWidth, iHeight);

		iPos = new Vector2f((GamePanel.WIDTH / 2 - iWidth / 2 + offset.getX()),
				(GamePanel.HEIGHT / 2 - iHeight / 2 + offset.getY()));
		lbPos = new Vector2f((iPos.getX() + iWidth / 2 + lbWidth / 2) - ((label.length()) * lbWidth / 2),
				iPos.getY() + iHeight / 2 - lbHeight / 2 - 4);

		this.bounds = new AABB(iPos, iWidth, iHeight);
	}

	public Button(String label, int lbWidth, int lbHeight, BufferedImage image, int iWidth, int iHeight) {
		this.label = label;
		this.lbWidth = lbWidth;
		this.lbHeight = lbHeight;
		this.image = image;
		this.hoverSize = 20;

		iPos = new Vector2f((GamePanel.WIDTH / 2 - iWidth / 2), (GamePanel.HEIGHT / 2 - iHeight / 2));
		lbPos = new Vector2f((iPos.getX() + iWidth / 2 + lbWidth / 2) - ((label.length()) * lbWidth / 2),
				iPos.getY() + iHeight / 2 - lbHeight / 2 - 4);

		this.bounds = new AABB(iPos, iWidth, iHeight);

		events = new ArrayList<ClickedEvent>();
	}

	public Button(String label, int lbWidth, int lbHeight, BufferedImage image, Vector2f iPos, int iWidth,
			int iHeight) {
		this(label, new Vector2f((iPos.getX() + iWidth / 2 + lbWidth / 2) - ((label.length()) * lbWidth / 2),
				iPos.getY() + iHeight / 2 - lbHeight / 2 - 4), lbWidth, lbHeight, image, iPos, iWidth, iHeight);
	}

	public Button(String label, Vector2f lbPos, int lbWidth, int lbHeight, BufferedImage image, Vector2f iPos,
			int iWidth, int iHeight) {
		this(label, lbWidth, lbHeight, image, iWidth, iHeight);

		this.iPos = iPos;
		this.lbPos = lbPos;

		this.bounds = new AABB(iPos, iWidth, iHeight);
	}

	public void addHoverImage(BufferedImage image) {
		this.hoverImage = image;
		this.canHover = true;
	}

	public void addPressedImage(BufferedImage image) {
		this.pressedImage = image;
	}

	public void setHoverSize(int size) {
		this.hoverSize = size;
	}

	public boolean getHovering() {
		return hovering;
	}

	public void setHover(boolean b) {
		this.canHover = b;
	}

	public void addEvent(ClickedEvent e) {
		events.add(e);
	}

	public int getWidth() {
		return (int) bounds.getWidth();
	}

	public int getHeight() {
		return (int) bounds.getHeight();
	}

	public Vector2f getPos() {
		return bounds.getPos();
	}

	public void addSlotEvent(SlotEvent e) {
		slotevents.add(e);
	}

	public void setSlot(Slots slot) {
		this.slot = slot;
	}

	public void update(double time) {
		if (pressedImage != null && pressed && pressedtime + 300 < time / 1000000) {
			pressed = false;
		}
	}

	private void hover(int value) {
		if (hoverImage == null) {
			iPos.setX(iPos.getX() - value / 2);
			iPos.setY(iPos.getY() - value / 2);
			float iWidth = value + bounds.getWidth();
			float iHeight = value + bounds.getHeight();
			this.bounds = new AABB(iPos, (int) iWidth, (int) iHeight);

			lbPos.setX(lbPos.getX() - value / 2);
			lbPos.setY(lbPos.getY() - value / 2);
			lbWidth += value / 3;
			lbHeight += value / 3;

		}

		hovering = true;
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		if (bounds.inside(mouse.getMouseX(), mouse.getMouseY())) {
			if (canHover && !hovering) {
				hover(hoverSize);
			}
			if (mouse.getMouseB() == 1 && !clicked) {
				clicked = true;
				pressed = true;

				pressedtime = System.nanoTime() / 1000000;

				for (int i = 0; i < events.size(); i++) {
					events.get(i).action(1);
				}
				if(slotevents == null) return;
                for(int i = 0; i < slotevents.size(); i++) {
                    slotevents.get(i).action(slot);
                }
			} else if (mouse.getMouseB() == -1) {
				clicked = false;
			}
		} else if (canHover && hovering) {
			hover(-hoverSize);
			hovering = false;
		}
	}

	public void render(Graphics2D g) {
		if (drawString) {
			SpriteSheet.drawArray(g, label, lbPos, lbWidth, lbHeight);
		}

		if (canHover && hoverImage != null && hovering) {
			g.drawImage(hoverImage, (int) iPos.getX(), (int) iPos.getY(), (int) bounds.getWidth(),
					(int) bounds.getHeight(), null);
		} else if (pressedImage != null && pressed) {
			g.drawImage(pressedImage, (int) iPos.getX(), (int) iPos.getY(), (int) bounds.getWidth(),
					(int) bounds.getHeight(), null);
		} else {
			g.drawImage(image, (int) iPos.getX(), (int) iPos.getY(), (int) bounds.getWidth(), (int) bounds.getHeight(),
					null);
		}

	}

	public interface ClickedEvent {
		void action(int mouseButton);
	}

	public interface SlotEvent {
		void action(Slots slot);
	}
}
