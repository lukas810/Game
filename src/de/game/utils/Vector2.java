package de.game.utils;

public class Vector2 {

	public float x;
	public float y;

	public Vector2() {
		x = 0;
		y = 0;
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void addX(float i) {
		x += i;
	}

	public void addY(float i) {
		y += i;
	}

	public void setVector(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
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
	
}
