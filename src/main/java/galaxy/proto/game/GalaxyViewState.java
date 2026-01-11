package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.planet.Coordinates;
import galaxy.core.planet.PlanetRef;
import galaxy.core.team.GalaxyView;
import galaxy.proto.controls.PlanetRefControl;
import jme3utilities.mesh.Icosphere;
import org.slf4j.Logger;
import shared.collision.CursorCollisions;
import shared.material.LightingMaterial;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);

	private static final float PLANET_SCALE = 0.001f;
	private static final float MIN_SCALE = 1f;

	private final Node galaxyViewNode = new Node("galaxy-view-node");

	private final Map<PlanetRef, Spatial> planetCache = new HashMap<>(1024);
	private final Map<MaterialCacheKey, Material> materialCache = new HashMap<>();

	@Override
	protected void initialize(Application app) {
		Mesh mesh = new Icosphere(1, 1f);

		materialCache.put(MaterialCacheKey.OWNED, new LightingMaterial(app.getAssetManager(), ColorRGBA.Green));
		materialCache.put(MaterialCacheKey.FRIENDLY, new LightingMaterial(app.getAssetManager(), ColorRGBA.Yellow));
		materialCache.put(MaterialCacheKey.HOSTILE, new LightingMaterial(app.getAssetManager(), ColorRGBA.Red));
		materialCache.put(MaterialCacheKey.VISITED, new LightingMaterial(app.getAssetManager(), ColorRGBA.LightGray));
		materialCache.put(MaterialCacheKey.UNKNOWN, new LightingMaterial(app.getAssetManager(), ColorRGBA.Gray));

		Entity player = getState(SinglePlayerGalaxyState.class).player();
		Collection<PlanetView> planets = getState(SinglePlayerGalaxyState.class).playerGalaxyView(player);

		planets.forEach(planet -> {
			Geometry geometry = new Geometry("p-%s".formatted(planet.planetRef()), mesh);

			geometry.setMaterial(resolveMaterial(player, planet));

			Coordinates coordinates = planet.coordinates();
			geometry.setLocalTranslation(new Vector3f(
					(float) coordinates.x(),
					(float) coordinates.y(),
					(float) coordinates.z()
			));

			float planetSize = (float) planet.size();
			// float scale = Math.max(MIN_SCALE, planetSize * PLANET_SCALE);
			float scale = 1f + planetSize * PLANET_SCALE;
			geometry.setLocalScale(scale);

			geometry.addControl(new PlanetRefControl(planet));

			galaxyViewNode.attachChild(geometry);

			planetCache.put(planet.planetRef(), geometry);
		});

		PlanetView playerHome = player.prop(GalaxyView.class).ownedPlanets().stream().filter(p -> p.size() == 1000.0).findFirst().orElseThrow();
		getState(GalaxyCameraState.class).centerOn(planetCache.get(playerHome.planetRef()), 64f);
	}

	private Material resolveMaterial(Entity race, PlanetView planet) {
		// TODO use diplomatic status too
		return switch (planet.visibility()) {
			case VISITED, ORBITING -> materialCache.get(MaterialCacheKey.VISITED);
			case OWNED -> materialCache.get(MaterialCacheKey.OWNED);
			default -> materialCache.get(MaterialCacheKey.UNKNOWN);
		};
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

	public Spatial spatialFor(PlanetRef planetRef) {
		return planetCache.get(planetRef);
	}

	private enum MaterialCacheKey {
		OWNED, FRIENDLY, HOSTILE, VISITED, UNKNOWN
	}

}
