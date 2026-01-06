package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.FastMath;
import domain.Race;
import domain.planet.Planet;
import generator.SeedSource;
import generator.sources.SphericalSeedSource;
import generator.PlanetGenerator;
import generator.simple.SimplePlanetGenerator;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;

public class GalaxyContextState extends BaseAppState {

	private final List<Race> races = new ArrayList<>();
	private final List<Planet> planets = new ArrayList<>();

	private Race player;

	@Override
	protected void initialize(Application app) {
		List<Race> _races = new ArrayList<>(10);

		for (int i = 0; i < 10; i++) {
			_races.add(new Race("race-%s".formatted(i), "Race %s".formatted(i)));
		}

		races.addAll(_races);

		SeedSource seedSource = new SphericalSeedSource(32768, 192, 42);
		PlanetGenerator generator = new SimplePlanetGenerator(new Generator(42), _races, 15, seedSource);

		planets.addAll(generator.planets());

		int randomIdx = FastMath.nextRandomInt(0, races.size() - 1);
		player = races.get(randomIdx);

		// add more planets to player for debug
		planets.stream().filter(p -> p.owner() == null).limit(24).forEach(p -> player.claim(p));
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
	}

	@Override
	protected void onDisable() {
	}

	public List<Race> allRaces() {
		return List.copyOf(races);
	}

	public Race player() {
		return player;
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
	}

	public List<Race> otherRaces() {
		return races.stream().filter(race -> !race.equals(player)).toList();
	}
}
