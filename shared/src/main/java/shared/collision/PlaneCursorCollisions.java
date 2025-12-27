package shared.collision;

import com.jme3.bounding.BoundingBox;
import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResult;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import java.util.Optional;

public class PlaneCursorCollisions {

	public enum Plane {
		XZ(Float.POSITIVE_INFINITY, 0, Float.POSITIVE_INFINITY),
		XY(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0),
		YZ(0, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

		private final float xExtent;
		private final float yExtent;
		private final float zExtent;

		Plane(float xExtent, float yExtent, float zExtent) {
			this.xExtent = xExtent;
			this.yExtent = yExtent;
			this.zExtent = zExtent;
		}

		public BoundingBox createBoundingBox(Vector3f pivot) {
			return new BoundingBox(pivot, xExtent, yExtent, zExtent);
		}
	}

	private final Collidable collidable;
	private final InputManager inputManager;
	private final Camera cam;

	public PlaneCursorCollisions(InputManager inputManager, Camera cam, Collidable collidable) {
		this.inputManager = inputManager;
		this.cam = cam;
		this.collidable = collidable;
	}

	public PlaneCursorCollisions(InputManager inputManager, Camera cam, Plane plane, Vector3f pivot) {
		this(inputManager, cam, plane.createBoundingBox(pivot));
	}

	public PlaneCursorCollisions(InputManager inputManager, Camera cam, Plane plane) {
		this(inputManager, cam, plane, new Vector3f());
	}

	public Optional<CollisionResult> collisions() {
		return new CursorCollisions(collidable, inputManager, cam).collisions();
	}
}
