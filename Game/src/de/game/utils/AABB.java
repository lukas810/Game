package de.game.utils;

import de.game.entity.Entity;

public class AABB {

	private Vector2f pos;
	private float width;
	private float height;
	private float radius;
	private int size;
	private Entity entity;

	private float xOffset = 0;
	private float yOffset = 0;

	public AABB(Vector2f pos, float width, float height) {
		this.pos = pos;
		this.width = width;
		this.height = height;

		size = (int) Math.max(width, height);
	}

	public AABB(Vector2f pos, int radius, Entity entity) {
		this.pos = pos;
		this.radius = radius;
		this.entity = entity;
		size = radius;
	}
	
	public AABB(Vector2f pos, int radius) {
        this.pos = pos;
        this.radius = radius;

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
		float ax = ((pos.getWorldVar().getX() + (xOffset)) + (width / 2));
		float ay = ((pos.getWorldVar().getY() + (yOffset)) + (height / 2));
		float bx = ((bBox.getPos().getWorldVar().getX() + (bBox.getxOffset())) + (width / 2));
		float by = ((bBox.getPos().getWorldVar().getY() + (bBox.getyOffset())) + (height / 2));

		if (Math.abs(ax - bx) < (width / 2) + (bBox.getWidth() / 2)) {
			if (Math.abs(ay - by) < (width / 2) + (bBox.getHeight() / 2)) {
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

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
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
}
