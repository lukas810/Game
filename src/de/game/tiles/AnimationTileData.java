package de.game.tiles;

public class AnimationTileData {

	private int animatedTileID;
	private int[] animatedTileIDs;
	private int duration;

	public AnimationTileData(int animatedTileID, int[] animatedTileIDs, int duration) {
		this.animatedTileID = animatedTileID;
		this.animatedTileIDs = animatedTileIDs;
		this.duration = duration;
	}

	public int getAnimatedTileID() {
		return animatedTileID;
	}

	public void setAnimatedTileID(int animatedTileID) {
		this.animatedTileID = animatedTileID;
	}

	public int[] getAnimatedTileIDs() {
		return animatedTileIDs;
	}

	public void setAnimatedTileIDs(int[] animatedTileIDs) {
		this.animatedTileIDs = animatedTileIDs;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
