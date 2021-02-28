package de.game.jbox2d;

import org.jbox2d.common.Vec2;

import de.game.GamePanel;
import de.game.utils.Vector2;


public class JBox2DHelper {
	
	private static float scaleFactor = 10;
	private static int yFlip = -1;
	
	public static Vector2 coordsWorldToPixel(float worldX, float worldY) {
		float pixelX = map(worldX, 0f, 1f, GamePanel.WIDTH / 2, GamePanel.WIDTH / 2 + scaleFactor);
		float pixelY = map(worldY, 0f, 1f, GamePanel.HEIGHT / 2, GamePanel.HEIGHT / 2 + scaleFactor);
		pixelY = map(pixelY,0f,GamePanel.HEIGHT, GamePanel.HEIGHT,0f);
		return new Vector2(pixelX, pixelY);
	}
	
	public static Vec2 coordsPixelToWorld(float pixelX, float pixelY) {
		float worldX = map(pixelX, GamePanel.WIDTH / 2, GamePanel.WIDTH / 2 + scaleFactor, 0f, 1f);
		float worldY = pixelY;
		worldY = map(pixelY, GamePanel.HEIGHT, 0f, 0f, GamePanel.HEIGHT);
		worldY = map(worldY, GamePanel.HEIGHT / 2, GamePanel.HEIGHT / 2 + scaleFactor, 0f, 1f);
		
		return new Vec2(worldX, worldY);
	}
	
	public static Vector2 vectorWorldToPixels(Vec2 v) {
		Vector2 u = new Vector2(v.x*scaleFactor,v.y*scaleFactor);
		u.setY(u.getY() * yFlip);;
		return u;
	}
	
	public static Vec2 vectorPixelsToWorld(float x, float y) {
		Vec2 u = new Vec2(x/scaleFactor,y/scaleFactor);
		u.y *= yFlip;
		return u;
	}
	
	
	public static float scalarPixelsToWorld(float val) {
		return val / scaleFactor;
	}
	
	private static float map(float value, float istart, float istop, float ostart, float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}
}
