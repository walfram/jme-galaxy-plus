package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResult;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.debug.WireBox;
import com.jme3.scene.shape.Line;
import galaxy.shared.PlaneXZCursorCollisions;
import galaxy.shared.material.UnshadedMaterial;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GizmosState extends BaseAppState {

	private static final Logger logger = getLogger(GizmosState.class);

	private final Node gizmosNode = new Node("galaxy-view-gizmos-scene");

	private Optional<CollisionResult> start = Optional.empty();
	private Optional<CollisionResult> end = Optional.empty();

	@Override
	protected void initialize(Application app) {

	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(gizmosNode);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(gizmosNode);
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
			// TODO if planet below XZ - "no value present", must use something else...
			to = new PlaneXZCursorCollisions(
					getApplication().getInputManager(),
					getApplication().getCamera()
			).collisions();
		}

		if (start.isPresent()) {
			CollisionResult from = start.get();
//			logger.debug("from = {}, to = {}", from, to);

			// TODO reuse mesh/geometry!
			// gizmosNode.detachAllChildren();

			Vector3f toWorldTranslation = to.map(cr ->
					Optional.ofNullable(cr.getGeometry()).map(Geometry::getWorldTranslation).orElse(cr.getContactPoint())
			).orElseThrow();

			logger.debug("distance = {}", from.getGeometry().getWorldTranslation().distance(toWorldTranslation));

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
