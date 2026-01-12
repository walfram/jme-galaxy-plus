package galaxy.ui.v2;

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
import com.simsilica.event.EventBus;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.planet.PlanetRef;
import galaxy.core.team.GalaxyView;
import galaxy.ui.v2.events.game.DiplomacyEvent;
import galaxy.ui.v2.events.ui.CameraEvent;
import galaxy.ui.v2.events.ui.ControlsEvent;
import galaxy.ui.v2.events.ui.GuiEvent;
import galaxy.ui.v2.events.ui.PlanetSelectEvent;
import jme3utilities.mesh.Icosphere;
import org.slf4j.Logger;
import shared.collision.CursorCollisions;
import shared.material.LightingMaterial;
import shared.mesh.FlatShadedMesh;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);

	private final Node galaxyNode = new Node("galaxy-node");

	private final Map<PlanetRef, Geometry> planetCache = new HashMap<>();
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

		Entity player = getState(GalaxyContextState.class).player();

		for (PlanetView planet : player.prop(GalaxyView.class).asCollection()) {
			Geometry geometry = new Geometry(planet.planetRef().value(), mesh);

			Material material = resolveMaterial(planet);

			geometry.setMaterial(material);
			geometry.setLocalTranslation(
					new Vector3f(
							(float) planet.coordinates().x(),
							(float) planet.coordinates().y(),
							(float) planet.coordinates().z()
					)
			);

			geometry.addControl(new PlanetRefControl(planet));

			float scale = (float) (1f + planet.size() * 0.001f);
			geometry.scale(scale);

			galaxyNode.attachChild(geometry);

			planetCache.put(planet.planetRef(), geometry);
		}
	}

	private Material resolveMaterial(PlanetView planet) {
		// TODO add FRIENDLY/HOSTILE etc
		return switch (planet.visibility()) {
			case OWNED -> materialCache.get(PlanetKey.OWNED);
			case VISITED -> materialCache.get(PlanetKey.VISITED);
			case ORBITING -> materialCache.get(PlanetKey.ORBITING);
			default -> materialCache.get(PlanetKey.UNKNOWN);
		};
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
			PlanetView planet = getState(GalaxyContextState.class).player().prop(GalaxyView.class).ownedPlanets().stream().findFirst().orElseThrow();
			EventBus.publish(CameraEvent.focusOn, new CameraEvent(planetCache.get(planet.planetRef())));
		});
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(galaxyNode);
	}

	@SuppressWarnings("unused")
	protected void changeDiplomacy(DiplomacyEvent event) {
		Entity player = getState(GalaxyContextState.class).player();

		for (PlanetView other : event.to().prop(GalaxyView.class).ownedPlanets()) {
			planetCache.get(other.planetRef()).setMaterial(resolveMaterial(other));
		}

	}

	@SuppressWarnings("unused")
	protected void selectPlanet(PlanetSelectEvent event) {
		Geometry geometry = planetCache.get(event.planet().planetRef());
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
				.map(g -> g.getControl(PlanetRefControl.class).planet())
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
		OWNED, FRIENDLY, HOSTILE, VISITED, ORBITING, UNKNOWN
	}

}
