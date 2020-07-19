package de.game.utils;

import de.game.entity.GameObject;

public class GameObjectKey {

	public float value;
	public GameObject go;

	public GameObjectKey(float value, GameObject go) {
		this.value = value;
		this.go = go;
	}

	public AABB getBounds() {
		return go.getBounds();
	}

}
