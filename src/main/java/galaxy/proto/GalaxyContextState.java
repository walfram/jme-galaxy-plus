package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import galaxy.generator.PlanetGenerator;
import galaxy.generator.SimplePlanetGenerator;
import galaxy.domain.Planet;

import java.util.ArrayList;
import java.util.List;

public class GalaxyContextState extends BaseAppState {

	private final List<Planet> planets = new ArrayList<>(1024);

	@Override
	protected void initialize(Application app) {
		PlanetGenerator simple = new SimplePlanetGenerator();
		planets.addAll(simple.planets());
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
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
}
