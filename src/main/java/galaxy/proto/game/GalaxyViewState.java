package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.proto.controls.PlanetRefControl;
import galaxy.shared.CursorCollisions;
import galaxy.shared.material.ShowNormalsMaterial;
import jme3utilities.mesh.Icosphere;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);

	private static final float PLANET_SCALE = 0.001f;
	private static final float MIN_SCALE = 1f;

	private final Node galaxyViewNode = new Node("galaxy-view-node");

	private final Map<Long, Spatial> planetCache = new HashMap<>(1024);

	@Override
	protected void initialize(Application app) {
		Mesh mesh = new Icosphere(1, 1f);
		Material material = new ShowNormalsMaterial(app.getAssetManager());

		Race player = getState(SinglePlayerGalaxyState.class).player();
		List<PlanetInfo> planets = getState(SinglePlayerGalaxyState.class).planetList(player);

		planets.forEach(planet -> {
			Geometry geometry = new Geometry("p-%s".formatted(planet.id()), mesh);
			geometry.setMaterial(material);
			geometry.setLocalTranslation(planet.coordinates().asVector3f());

			float planetSize = (float) planet.size().value();
			float scale = Math.max(MIN_SCALE, planetSize * PLANET_SCALE);
			geometry.setLocalScale(scale);

			geometry.addControl(new PlanetRefControl(planet));

			galaxyViewNode.attachChild(geometry);

			planetCache.put(planet.id(), geometry);
		});

		Planet playerHome = player.ownedPlanets().stream().filter(p -> p.size().value() == 1000.0).findFirst().orElseThrow();
		getState(GalaxyCameraState.class).centerOn(planetCache.get(playerHome.id()), 64f);
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		logger.debug("Enabling galaxy view state");
		((SimpleApplication) getApplication()).getRootNode().attachChild(galaxyViewNode);
	}

	@Override
	protected void onDisable() {
		logger.debug("Disabling galaxy view state");
		((SimpleApplication) getApplication()).getRootNode().detachChild(galaxyViewNode);
	}

	public Optional<CollisionResult> cursorCollision() {
		return new CursorCollisions(
				galaxyViewNode,
				getApplication().getInputManager(),
				getApplication().getCamera()
		).collisions();
	}

	public Spatial spatialFor(Planet planet) {
		return planetCache.get(planet.id());
	}

}
