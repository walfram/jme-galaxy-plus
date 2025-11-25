package galaxy.shared;

import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import java.util.Optional;

public class PlaneXZCursorCollisions {

	private final BoundingBox collidable = new BoundingBox(new Vector3f(), Float.POSITIVE_INFINITY, 0, Float.POSITIVE_INFINITY);

	private final InputManager inputManager;
	private final Camera cam;

	public PlaneXZCursorCollisions(InputManager inputManager, Camera cam) {
		this.inputManager = inputManager;
		this.cam = cam;
	}


	public Optional<CollisionResult> collisions() {
		return new CursorCollisions(collidable, inputManager, cam).collisions();
	}
}
