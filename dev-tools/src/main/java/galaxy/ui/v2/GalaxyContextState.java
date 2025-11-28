package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.generator.PlanetGenerator;
import galaxy.generator.SeedSource;
import galaxy.generator.simple.SimplePlanetGenerator;
import galaxy.generator.sources.SphericalSeedSource;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;

public class GalaxyContextState extends BaseAppState {

	private final List<Race> races = new ArrayList<>();
	private final List<Planet> planets = new ArrayList<>();

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

	public List<Race> races() {
		return List.copyOf(races);
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
	}

}
