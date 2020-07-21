package de.game.utils;

import java.util.ArrayList;

import de.game.entity.GameObject;

public class AABB {

	private Vector2f pos;
	private float width;
	private float height;
	private float radius;
	private int size;

	private float xOffset = 0;
	private float yOffset = 0;

	private float surfaceArea;

	public AABB(Vector2f pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.surfaceArea = width * height;

		size = Math.max(width, height);
	}

	public AABB(Vector2f pos, int radius) {
		this.pos = pos;
		this.radius = radius;
		this.surfaceArea = (float) Math.PI * (radius * radius);

		size = radius;
	}

	public void setBox(Vector2f pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;

		size = Math.max(width, height);
	}

	public void setCircle(Vector2f pos, int radius) {
		this.pos = pos;
		this.radius = radius;
		size = radius;
	}

	public boolean collides(AABB bBox) {
		return collides(0, 0, bBox);
	}

	public boolean collides(float dx, float dy, ArrayList<GameObject> go) {
		boolean collides = false;

		for (int i = 0; i < go.size(); i++) {
			collides = collides(dx, dy, go.get(i).getBounds());
			if (collides) {
				go.get(i).getImage().restoreDefault();
				go.remove(i);
				return collides;
			}
		}

		return collides;
	}

	public boolean collides(float dx, float dy, AABB bBox) {
		float ax = ((pos.getX() + (xOffset)) + (width / 2)) + dx;
		float ay = ((pos.getY() + (yOffset)) + (height / 2)) + dy;
		float bx = ((bBox.getPos().getX() + (bBox.getxOffset())) + (bBox.getWidth() / 2));
		float by = ((bBox.getPos().getY() + (bBox.getyOffset())) + (bBox.getHeight() / 2));

		if (Math.abs(ax - bx) < (width / 2) + (bBox.getWidth() / 2)) {
			if (Math.abs(ay - by) < (height / 2) + (bBox.getHeight() / 2)) {
				return true;
			}
		}

		return false;
	}

	public boolean collisionCircleBox(AABB aBox) {

		float dx = Math.max(aBox.getPos().getX() + aBox.getxOffset(),
				Math.min(pos.getX() + (radius / 2), aBox.getPos().getX() + aBox.getxOffset() + aBox.getWidth()));
		float dy = Math.max(aBox.getPos().getY() + aBox.getyOffset(),
				Math.min(pos.getY() + (radius / 2), aBox.getPos().getY() + aBox.getyOffset() + aBox.getHeight()));

		dx = pos.getX() + (radius / 2) - dx;
		dy = pos.getY() + (radius / 2) - dy;

		if (Math.sqrt(dx * dx + dy * dy) < radius / 2) {
			return true;
		}

		return false;

	}

	public boolean intersect(AABB aBox) {

		if ((pos.getX() + xOffset > aBox.getPos().getX() + aBox.getxOffset() + aBox.getSize())
				|| (aBox.getPos().getX() + xOffset > pos.getX() + aBox.getxOffset() + aBox.getSize())) {
			return false;
		}

		if ((pos.getY() + yOffset > aBox.getPos().getY() + aBox.getyOffset() + aBox.getSize())
				|| (aBox.getPos().getY() + yOffset > pos.getY() + aBox.getyOffset() + aBox.getSize())) {
			return false;
		}

		return true;
	}
	
	public boolean inside(int xp, int yp) {
        if(xp == -1 || yp == - 1) return false;

        int wTemp = (int) this.width;
        int hTemp = (int) this.height;
        int x = (int) this.pos.getX();
        int y = (int) this.pos.getY();

        if(xp < x || yp < y) {
            return false;
        }

        wTemp += x;
        hTemp += y;
        return ((wTemp < x || wTemp > xp) && (hTemp < y || hTemp > yp));
    }

	
	public float distance(Vector2f other) {
		float dx = pos.getX() - other.getX();
		float dy = pos.getY() - other.getY();
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public AABB merge(AABB other) {
		float minX = Math.min(pos.getX(), other.getPos().getX());
		float minY = Math.min(pos.getY(), other.getPos().getY());

		int maxW = (int) Math.max(width, other.getWidth());
		int maxH = (int) Math.max(height, other.getHeight());

		Vector2f pos = new Vector2f(minX, minY);
		return new AABB(pos, maxW, maxH);
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

}
