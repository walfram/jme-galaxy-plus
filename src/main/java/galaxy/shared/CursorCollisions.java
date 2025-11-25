package galaxy.shared;

import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import java.util.Optional;

public class CursorCollisions {

	private final Collidable collidable;
	private final InputManager inputManager;
	private final Camera cam;

	public CursorCollisions(Collidable collidable, InputManager inputManager, Camera cam) {
		this.collidable = collidable;
		this.inputManager = inputManager;
		this.cam = cam;
	}

	public Optional<CollisionResult> collisions() {
		CollisionResults results = new CollisionResults();
		Vector2f click2d = inputManager.getCursorPosition();
		Vector3f click3d = cam.getWorldCoordinates(click2d, 0f).clone();
		Vector3f dir = cam.getWorldCoordinates(click2d, 1f).subtractLocal(click3d).normalizeLocal();
		Ray ray = new Ray(click3d, dir);

		int collisions = collidable.collideWith(ray, results);

		if (collisions > 0) {
			return Optional.of(results.getClosestCollision());
		} else {
			return Optional.empty();
		}
	}

}
