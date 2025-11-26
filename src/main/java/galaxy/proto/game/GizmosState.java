package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.debug.WireBox;
import com.jme3.scene.shape.Line;
import com.jme3.scene.shape.Quad;
import galaxy.shared.PlaneCursorCollisions;
import galaxy.shared.material.UnshadedMaterial;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GizmosState extends BaseAppState {

	private static final Logger logger = getLogger(GizmosState.class);

	private final Node gizmosNode = new Node("galaxy-view-gizmos-scene");

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private Optional<CollisionResult> start = Optional.empty();

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private Optional<CollisionResult> end = Optional.empty();
	private Geometry collisionFallback;

	@Override
	protected void initialize(Application app) {
		float width = app.getCamera().getWidth();
		float height = app.getCamera().getHeight();

		collisionFallback = new Geometry("blocker", new Quad(width, height));
		Material material = new UnshadedMaterial(app.getAssetManager(), ColorRGBA.Green);
		collisionFallback.setMaterial(material);
		collisionFallback.setLocalTranslation(0, 0, -32);

		collisionFallback.setCullHint(Geometry.CullHint.Always);
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(gizmosNode);
		((SimpleApplication) getApplication()).getGuiNode().attachChild(collisionFallback);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(gizmosNode);
		((SimpleApplication) getApplication()).getGuiNode().detachChild(collisionFallback);
	}

	public void markDragStart() {
		start = getState(GalaxyViewState.class).cursorCollision();
		logger.debug("distance measure start, {}", start);
	}

	public void markDragEnd() {
		logger.debug("distance measure end");
		end = getState(GalaxyViewState.class).cursorCollision();

		end.map(CollisionResult::getGeometry).ifPresent(to -> {
			start.map(CollisionResult::getGeometry).ifPresent(from -> {
				if (Objects.equals(to, from)) {
					getState(GalaxyCameraState.class).centerOn(to);
					getState(GalaxyUiState.class).showPlanetInfo(to);
				} else {
					getState(GalaxyUiState.class).showDistance(from, to);
				}
			});
		});
	}

	public void notifyMeasureDistanceUpdate() {
		Optional<CollisionResult> to = getState(GalaxyViewState.class).cursorCollision();

		if (to.isEmpty()) {
			to = new PlaneCursorCollisions(
					getApplication().getInputManager(),
					getApplication().getCamera(),
					PlaneCursorCollisions.Plane.XZ,
					start.map(CollisionResult::getContactPoint).orElse(Vector3f.ZERO)
			).collisions()
					.or(() ->
							new PlaneCursorCollisions(
									getApplication().getInputManager(), getApplication().getCamera(),
									PlaneCursorCollisions.Plane.XY,
									start.map(CollisionResult::getContactPoint).orElse(Vector3f.ZERO)
							).collisions()
					)
					.or(() ->
							new PlaneCursorCollisions(
									getApplication().getInputManager(), getApplication().getCamera(),
									PlaneCursorCollisions.Plane.YZ,
									start.map(CollisionResult::getContactPoint).orElse(Vector3f.ZERO)
							).collisions()
					);
		}

		if (start.isPresent()) {
			CollisionResult from = start.get();
			// TODO reuse mesh/geometry!
			// gizmosNode.detachAllChildren();

			Vector3f toWorldTranslation = to.map(cr ->
					Optional.ofNullable(cr.getGeometry()).map(Geometry::getWorldTranslation).orElse(cr.getContactPoint())
			).orElseThrow();

			Geometry geometry = new Geometry(
					"distance-line",
					new Line(from.getGeometry().getWorldTranslation(), toWorldTranslation)
			);
			geometry.setMaterial(new UnshadedMaterial(getApplication().getAssetManager(), ColorRGBA.Yellow));

			gizmosNode.detachChildNamed(geometry.getName());
			gizmosNode.attachChild(geometry);
		}

	}

	public void notifyHover() {
		getState(GalaxyViewState.class).cursorCollision().ifPresent(cr -> {
			Geometry geometry = cr.getGeometry();

			BoundingBox bound = (BoundingBox) geometry.getWorldBound();

			Geometry cursor = WireBox.makeGeometry(bound);
			cursor.setName("cursor");
			cursor.setMaterial(new UnshadedMaterial(getApplication().getAssetManager(), ColorRGBA.Red));

			gizmosNode.detachChildNamed(cursor.getName());
			gizmosNode.attachChild(cursor);
		});
	}
}
