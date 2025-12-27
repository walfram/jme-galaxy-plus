package shared.collision;

import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;

import java.util.Optional;

public class PlaneXZCameraCollisions {

	private final BoundingBox collidable = new BoundingBox(new Vector3f(), Float.POSITIVE_INFINITY, 0, Float.POSITIVE_INFINITY);

	private final Vector3f cameraLocation;
	private final Vector3f cameraDirection;

	public PlaneXZCameraCollisions(Vector3f cameraLocation, Vector3f cameraDirection) {
		this.cameraLocation = cameraLocation;
		this.cameraDirection = cameraDirection;
	}

	public Optional<CollisionResult> collisions() {
		BoundingBox collidable = new BoundingBox(new Vector3f(), Float.POSITIVE_INFINITY, 0, Float.POSITIVE_INFINITY);

		Ray ray = new Ray(cameraLocation, cameraDirection);
		CollisionResults results = new CollisionResults();

		int collisions = collidable.collideWith(ray, results);

		if (collisions > 0) {
			return Optional.of(results.getClosestCollision());
		} else {
			return Optional.empty();
		}
	}

}
