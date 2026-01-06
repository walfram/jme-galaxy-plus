package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import domain.Race;
import domain.planet.Planet;
import domain.planet.PlanetInfo;
import domain.planet.info.OwnedPlanet;
import domain.planet.info.UnknownPlanet;
import domain.planet.info.VisiblePlanet;
import domain.planet.info.VisitedPlanet;
import galaxy.generator.SeedSource;
import galaxy.generator.sources.SimpleSeedSource;
import galaxy.proto.menu.GameConfig;
import generator.PlanetGenerator;
import galaxy.generator.simple.SimplePlanetGenerator;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;
import shared.debug.DebugPointMesh;
import shared.material.UnshadedMaterial;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SinglePlayerGalaxyState extends BaseAppState {

	private static final Logger logger = getLogger(SinglePlayerGalaxyState.class);

	private final Node debugNode = new Node("galaxy-context-debug-node");

	private Race player;

	private final List<Race> races = new ArrayList<>(128);
	private final List<Planet> planets = new ArrayList<>(1024);

	private final GameConfig gameConfig;

	public SinglePlayerGalaxyState(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
	}

	@Override
	protected void initialize(Application app) {
		logger.info("Initializing galaxy context state");
		logger.info("game config = {}", gameConfig);

		for (int raceIdx = 0; raceIdx < gameConfig.raceCount(); raceIdx++) {
			Race race = new Race("race-id-%s".formatted(raceIdx), "race-%s".formatted(raceIdx), new ArrayList<>());
			races.add(race);
			logger.info("race = {}", race);
		}

		player = races.getFirst();

		Generator random = new Generator();
		int seedCount = (int) (16384 * races.size() * 0.1);
		double scale = 256 * races.size() * 0.1;
		SeedSource seedSource = new SimpleSeedSource(seedCount, 160f);
//		SeedSource seedSource = new GoldenSpiralSeedSource(gameConfig.raceCount(), 16384, 256, 42L);

		PlanetGenerator simple = new SimplePlanetGenerator(random, races, gameConfig.planetsPerRace(), seedSource);
		planets.addAll(simple.planets());

//		planets.forEach(p -> logger.info("planet = {}", p));

		List<Vector3f> points = seedSource.points();
		Geometry debug = new Geometry("debug-seed-points", new DebugPointMesh(points));
		debug.setMaterial(new UnshadedMaterial(app.getAssetManager(), ColorRGBA.Red));
		debug.getMaterial().setFloat("PointSize", 1f);
//		debugNode.attachChild(debug);
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(debugNode);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(debugNode);
	}

	public Race player() {
		return player;
	}

	public List<PlanetInfo> planetList(Race race) {
		return planets.stream()
				.map(p -> {
							if (race.ownedPlanet(p.id()).isPresent()) {
								return new OwnedPlanet(p);
							} else if (race.visiblePlanet(p.id()).isPresent()) {
								return new VisiblePlanet(p);
							} else if (race.visitedPlanet(p.id()).isPresent()) {
								return new VisitedPlanet(p);
							} else {
								return new UnknownPlanet(p);
							}
						}
				)
				.toList();
	}
}
