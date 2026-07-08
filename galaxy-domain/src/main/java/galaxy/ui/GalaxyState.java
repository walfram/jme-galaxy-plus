package galaxy.ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import galaxy.domain.*;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;
import shared.debug.DebugPointMesh;
import shared.material.UnshadedMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyState.class);

	private final Node scene = new Node("galaxy-debug-node");
	private final List<Planet> planets = new ArrayList<>(256);

	@Override
	protected void initialize(Application app) {
		Generator random = new Generator(42L);

		List<Vector3f> seedPoints = new ArrayList<>(4096);
		for (int idx = 0; idx < 4096; idx++) {
			seedPoints.add(random.nextVector3f().multLocal(256f));
		}

		Geometry geometry = new Geometry("seed-points", new DebugPointMesh(seedPoints));
		geometry.setMaterial(new UnshadedMaterial(app.getAssetManager(), ColorRGBA.Yellow));
//		scene.attachChild(geometry);

		List<Vector3f> originSeedPoints = new ArrayList<>(seedPoints);

		int playerCount = 8;
		List<Vector3f> origins = new ArrayList<>(playerCount);
		float minDistanceSquared = 30f * 30f;

		AtomicInteger idSource = new AtomicInteger(0);

		for (int idx = 0; idx < playerCount; idx++) {
			Vector3f origin = random.pick(originSeedPoints);
			originSeedPoints.remove(origin);
			origins.add(origin);

			List<Vector3f> removed = originSeedPoints.stream().filter(p -> p.distanceSquared(origin) <= minDistanceSquared).toList();
			originSeedPoints.removeAll(removed);

			Planet home = new Planet(new PlanetId(idSource.incrementAndGet()), new Size(1000f), new Resources(10f), new Coordinates(origin), new Population(1000f), new Industry(1000f));
			planets.add(home);

			Vector3f alphaOffset = random.nextUnitVector3f().multLocal(random.nextFloat(5f, 10f));
			Planet alpha = new Planet(new PlanetId(idSource.incrementAndGet()), new Size(500f), new Resources(10f), new Coordinates(origin.add(alphaOffset)), new Population(500f), new Industry(500f));
			planets.add(alpha);

			Vector3f betaOffset = random.nextUnitVector3f().multLocal(random.nextFloat(5f, 10f));
			Planet beta = new Planet(new PlanetId(idSource.incrementAndGet()), new Size(500f), new Resources(10f), new Coordinates(origin.add(betaOffset)), new Population(500f), new Industry(500f));
			planets.add(beta);
		}

		logger.debug("player count = {}, hw/dw = {}", playerCount, planets.size());
		int planetsPerPlayer = 20;
		int remainingPlanets = playerCount * planetsPerPlayer - planets.size();
		logger.debug("remaining planets = {}", remainingPlanets);

		seedPoints.removeAll(origins);

		float minSize = 1f, maxSize = 2500f;
		float minRes = 0.05f, maxRes = 25f;

		for (int idx = 0; idx < remainingPlanets; idx++) {
			Vector3f origin = random.pick(seedPoints);
			seedPoints.remove(origin);

			float size = random.nextFloat(1f, 2500f);
			// Normalize size to 0..1, then invert (1 - t)
			float t = (size - minSize) / (maxSize - minSize);
			float resources = maxRes - t * (maxRes - minRes);

			Planet planet = new Planet(new PlanetId(idSource.incrementAndGet()), new Size(size), new Resources(resources), new Coordinates(origin));
			planets.add(planet);
		}

		logger.debug("total planets = {}", planets.size());
	}

	public List<Planet> planets() {
		return planets;
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

	public void dumpPlanetInfo(int planetId) {
		Planet planet = planets.stream().filter(p -> p.id() == planetId).findFirst().orElseThrow();
		logger.info("dumping planet info {}", planet);
	}

	public Planet planetById(int planetId) {
		return planets.stream().filter(p -> p.id() == planetId).findFirst().orElseThrow();
	}
}
