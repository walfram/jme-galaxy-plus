package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import galaxy.domain.planet.Planet;
import galaxy.shared.collision.CursorCollisions;
import galaxy.shared.material.UnshadedMaterial;
import galaxy.ui.v2.events.CameraEvent;
import galaxy.ui.v2.events.ControlsEvent;
import galaxy.ui.v2.events.GuiEvent;
import jme3utilities.mesh.Icosphere;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);

	private final Node galaxyNode = new Node("galaxy-node");

	private final Map<Planet, Geometry> cache = new HashMap<>();

	@Override
	protected void initialize(Application app) {
		Mesh mesh = new Icosphere(1, 1);

		Material material = new UnshadedMaterial(app.getAssetManager(), ColorRGBA.Gray);

		for (Planet planet : getState(GalaxyContextState.class).planets()) {
			Geometry geometry = new Geometry(planet.name(), mesh);

			geometry.setMaterial(material);
			geometry.setLocalTranslation(planet.coordinates().asVector3f());

			geometry.addControl(new PlanetRef(planet));

			float scale = (float) (1f + planet.size().value() * 0.001f);
			geometry.scale(scale);

			galaxyNode.attachChild(geometry);

			cache.put(planet, geometry);
		}
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(galaxyNode);

		EventBus.addListener(this, ControlsEvent.selectPlanet);

		getApplication().enqueue(() -> {
			Planet planet = getState(GalaxyContextState.class).player().ownedPlanets().getFirst();
			EventBus.publish(CameraEvent.focusOn, new CameraEvent(cache.get(planet)));
		});
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(galaxyNode);
	}

	protected void selectPlanet(ControlsEvent event) {
		Optional<CollisionResult> collisions = new CursorCollisions(
				galaxyNode, getApplication().getInputManager(), getApplication().getCamera()
		).collisions();

		collisions
				.map(CollisionResult::getGeometry)
				.map(geometry -> {
					EventBus.publish(CameraEvent.focusOn, new CameraEvent(geometry));
					return geometry;
				})
				.map(g -> g.getControl(PlanetRef.class).planet())
				.ifPresentOrElse(
						p -> {
							logger.debug("select planet = {}", p);
							EventBus.publish(GuiEvent.planetSelected, new GuiEvent(p));
						},
						() -> {
							logger.debug("no planet selected");
							EventBus.publish(GuiEvent.planetUnselected, new GuiEvent(null));
						}
				);
	}

}
