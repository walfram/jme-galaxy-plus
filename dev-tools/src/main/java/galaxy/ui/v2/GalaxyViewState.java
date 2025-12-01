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
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.shared.collision.CursorCollisions;
import galaxy.shared.material.LightingMaterial;
import galaxy.shared.mesh.FlatShadedMesh;
import galaxy.ui.v2.events.game.DiplomacyEvent;
import galaxy.ui.v2.events.ui.CameraEvent;
import galaxy.ui.v2.events.ui.ControlsEvent;
import galaxy.ui.v2.events.ui.GuiEvent;
import galaxy.ui.v2.events.ui.PlanetSelectEvent;
import jme3utilities.mesh.Icosphere;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);

	private final Node galaxyNode = new Node("galaxy-node");

	private final Map<Planet, Geometry> planetCache = new HashMap<>();
	private final Map<Object, Material> materialCache = new HashMap<>();

	@Override
	protected void initialize(Application app) {
		Mesh mesh = new FlatShadedMesh(new Icosphere(1, 1));

		// Material material = new LightingMaterial(app.getAssetManager(), ColorRGBA.Gray);
		materialCache.put(PlanetKey.OWNED, new LightingMaterial(app.getAssetManager(), ColorRGBA.Green));
		materialCache.put(PlanetKey.FRIENDLY, new LightingMaterial(app.getAssetManager(), ColorRGBA.Yellow));
		materialCache.put(PlanetKey.HOSTILE, new LightingMaterial(app.getAssetManager(), ColorRGBA.Red));
		materialCache.put(PlanetKey.VISITED, new LightingMaterial(app.getAssetManager(), ColorRGBA.LightGray));
		materialCache.put(PlanetKey.UNKNOWN, new LightingMaterial(app.getAssetManager(), ColorRGBA.Gray));

		Race player = getState(GalaxyContextState.class).player();

		for (Planet planet : getState(GalaxyContextState.class).planets()) {
			Geometry geometry = new Geometry(planet.name(), mesh);

			Material material = resolveMaterial(player, planet);

			geometry.setMaterial(material);
			geometry.setLocalTranslation(planet.coordinates().asVector3f());

			geometry.addControl(new PlanetRef(planet));

			float scale = (float) (1f + planet.size().value() * 0.001f);
			geometry.scale(scale);

			galaxyNode.attachChild(geometry);

			planetCache.put(planet, geometry);
		}
	}

	private Material resolveMaterial(Race race, Planet planet) {
		if (race.ownedPlanet(planet.id()).isPresent())
			return materialCache.get(PlanetKey.OWNED);

		if (race.friendlyPlanet(planet.id()).isPresent())
			return materialCache.get(PlanetKey.FRIENDLY);

		if (race.hostilePlanet(planet.id()).isPresent())
			return materialCache.get(PlanetKey.HOSTILE);

		if (race.visitedPlanet(planet.id()).isPresent())
			return materialCache.get(PlanetKey.VISITED);

		return materialCache.get(PlanetKey.UNKNOWN);
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(galaxyNode);

		EventBus.addListener(this, ControlsEvent.selectPlanet);
		EventBus.addListener(this, PlanetSelectEvent.selectPlanet);

		EventBus.addListener(this, DiplomacyEvent.changeDiplomacy);

		getApplication().enqueue(() -> {
			Planet planet = getState(GalaxyContextState.class).player().ownedPlanets().getFirst();
			EventBus.publish(CameraEvent.focusOn, new CameraEvent(planetCache.get(planet)));
		});
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(galaxyNode);
	}

	@SuppressWarnings("unused")
	protected void changeDiplomacy(DiplomacyEvent event) {
		Race player = getState(GalaxyContextState.class).player();

		for (Planet other : event.to().ownedPlanets()) {
			planetCache.get(other).setMaterial(resolveMaterial(player, other));
		}

	}

	@SuppressWarnings("unused")
	protected void selectPlanet(PlanetSelectEvent event) {
		Geometry geometry = planetCache.get(event.planet());
		EventBus.publish(CameraEvent.focusOn, new CameraEvent(geometry));
	}

	@SuppressWarnings("unused")
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

	private enum PlanetKey {
		OWNED, FRIENDLY, HOSTILE, VISITED, UNKNOWN
	}

}
