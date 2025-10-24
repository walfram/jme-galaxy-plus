package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import galaxy.domain.Planet;
import galaxy.generator.PlanetGenerator;
import galaxy.generator.SeedSource;
import galaxy.generator.SimpleSeedSource;
import galaxy.generator.simple.SimplePlanetGenerator;
import galaxy.shared.DebugPointMesh;
import galaxy.shared.UnshadedMaterial;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;

public class GalaxyContextState extends BaseAppState {

	private final Node debugNode = new Node("galaxy-context-debug-node");

	private final List<Planet> planets = new ArrayList<>(1024);

	@Override
	protected void initialize(Application app) {
		Generator random = new Generator();
		SeedSource seedSource = new SimpleSeedSource();

		PlanetGenerator simple = new SimplePlanetGenerator(random, 10, 10, seedSource);
		planets.addAll(simple.planets());

		List<Vector3f> points = seedSource.points();
		Geometry debug = new Geometry("debug-seed-points", new DebugPointMesh(points));
		debug.setMaterial(new UnshadedMaterial(app.getAssetManager(), ColorRGBA.Red));
		debug.getMaterial().setFloat("PointSize", 1f);
		debugNode.attachChild(debug);
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		// ((SimpleApplication) getApplication()).getRootNode().attachChild(debugNode);
	}

	@Override
	protected void onDisable() {
		// ((SimpleApplication) getApplication()).getRootNode().detachChild(debugNode);
	}
}
