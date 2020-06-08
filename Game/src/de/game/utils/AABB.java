package de.game.utils;

import de.game.entity.Entity;
import de.game.tiles.TileMapObject;

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

		float cx = (float) (pos.getWorldVar().getX() + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
		float cy = (float) (pos.getWorldVar().getY() + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));

		// yDelta aBox.getWidth()
		float xDelta = cx - Math.max(aBox.getPos().getWorldVar().getX() + (aBox.getWidth() / 2),
				Math.min(cx, aBox.getPos().getX()));
		float yDelta = cy - Math.max(aBox.getPos().getWorldVar().getY() + (aBox.getHeight() / 2),
				Math.min(cy, aBox.getPos().getY()));

		return ((xDelta * xDelta + yDelta * yDelta) < ((radius / Math.sqrt(2)) * (radius / Math.sqrt(2))));

	}

	public boolean collisionTile(float ax, float ay) {

		for (int i = 0; i < 4; i++) {
			int xt = (int) ((pos.getX() + ax) + (i % 2) * width + xOffset) / 16;
			int yt = (int) ((pos.getY() + ay) + ((int)(i / 2)) * height + yOffset) / 16;
			
			
			if(TileMapObject.tileMapObjectBlocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
				
				return TileMapObject.tileMapObjectBlocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(this);
			}
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
