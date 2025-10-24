package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import galaxy.domain.Planet;
import galaxy.shared.ShowNormalsMaterial;
import jme3utilities.mesh.Icosphere;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyViewState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyViewState.class);
	private static final float PLANET_SCALE = 0.001f;
	private static final float MIN_SCALE = 1f;

	private final Node galaxyViewNode = new Node("galaxy-view-node");

	@Override
	protected void initialize(Application app) {
		Mesh mesh = new Icosphere(1, 1f);
		Material material = new ShowNormalsMaterial(app.getAssetManager());

		List<Planet> planets = getState(GalaxyContextState.class).planets();

		planets.forEach(planet -> {
			Geometry geometry = new Geometry(planet.id(), mesh);
			geometry.setMaterial(material);
			geometry.setLocalTranslation(planet.coordinates().asVector3f());

			float planetSize = planet.size().value();
			float scale = Math.max(MIN_SCALE, planetSize * PLANET_SCALE);
			geometry.setLocalScale(scale);

			galaxyViewNode.attachChild(geometry);
		});
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
}
