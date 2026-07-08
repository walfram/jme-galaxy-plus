package galaxy.ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import galaxy.domain.Planet;
import jme3utilities.mesh.Octasphere;
import org.slf4j.Logger;
import shared.collision.CursorCollisions;
import shared.material.LightingMaterial;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);

	private final Node scene = new Node("galaxy-view-node");

	@Override
	protected void initialize(Application app) {
		Mesh mesh = new Octasphere(1, 1f);
		Material material = new LightingMaterial(app.getAssetManager(), ColorRGBA.Gray);

		List<Planet> planets = getState(GalaxyState.class).planets();
		for (Planet planet : planets) {
			float scale = Math.max(1f, planet.size() / 1000f);

			Geometry geometry = new Geometry("planet#%s".formatted(planet.id()), mesh);
			geometry.setMaterial(material);
			geometry.scale(scale);
			geometry.setLocalTranslation(planet.coordinates().asVector3f());

			geometry.addControl(new PlanetIdControl(planet.id()));

			scene.attachChild(geometry);
		}
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(scene);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(scene);
	}

	public void handleClick() {
		CursorCollisions cursorCollisions = new CursorCollisions(scene, getApplication().getInputManager(), getApplication().getCamera());

		cursorCollisions.collisions().ifPresentOrElse(collision -> {
			Geometry geometry = collision.getGeometry();
			logger.debug("click on {}", geometry.getName());
			int planetId = geometry.getControl(PlanetIdControl.class).planetId();
			getState(GalaxyState.class).dumpPlanetInfo(planetId);
			Planet planet = getState(GalaxyState.class).planetById(planetId);
			EventBus.publish(UiEvent.planetSelected, new UiEvent(planet));
		}, () -> {
			EventBus.publish(UiEvent.planetUnselected, new UiEvent(null));
		});
	}
}
