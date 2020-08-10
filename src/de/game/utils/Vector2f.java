package de.game.utils;

public class Vector2f {

	private float x;
	private float y;

	public static float worldX;
	public static float worldY;

	public Vector2f() {
		x = 0;
		y = 0;
	}

	public Vector2f(Vector2f vec) {
		new Vector2f(vec.x, vec.y);
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void addX(float i) {
		x += i;
	}

	public void addY(float i) {
		y += i;
	}

	public void setVector(Vector2f vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static void setWorldVar(float x, float y) {
		worldX = x;
		worldY = y;
	}

	public Vector2f getWorldVar() {
		return new Vector2f(x - worldX, y - worldY);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public static float getWorldVarX(float x) {
        return x - worldX;
    }

    public static float getWorldVarY(float y) {
        return y - worldY;
    }

}
