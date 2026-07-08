package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.FastMath;
import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.PlanetVisibility;
import galaxy.core.planet.Planet;
import galaxy.core.team.GalaxyView;
import galaxy.core.team.TeamRef;
import generator.SeedSource;
import generator.impl.classic.ClassicGeneratedGalaxy;
import generator.sources.SimpleSeedSource;
import generator.sources.SphericalSeedSource;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;

public class GalaxyContextState extends BaseAppState {

	private final List<Entity> teams = new ArrayList<>();

	private Context galaxy;
	private Entity player;

	@Override
	protected void initialize(Application app) {
		SeedSource seedSource = new SimpleSeedSource(32768, 192f, 42);
		galaxy = new ClassicGeneratedGalaxy(10, 10, seedSource, 42L).generate();

		teams.addAll(galaxy.teams().values());

		int randomIdx = FastMath.nextRandomInt(0, teams.size() - 1);
		player = teams.get(randomIdx);

		// add more planets to player for debug
		galaxy.planets().values().stream()
				.filter(e -> !e.has(TeamRef.class))
				.limit(24)
				.forEach(e -> player.prop(GalaxyView.class).changeVisibility(e, PlanetVisibility.OWNED));
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

	public Entity player() {
		return player;
	}

	public List<PlanetView> planets() {
		return List.copyOf(player.prop(GalaxyView.class).asCollection());
	}

	public List<Entity> otherRaces() {
		return teams.stream().filter(race -> !race.equals(player)).toList();
	}
}
