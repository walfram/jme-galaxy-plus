package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import galaxy.core.ClassicGalaxyBootstrap;
import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.team.GalaxyView;
import galaxy.proto.menu.GameConfig;
import generator.SeedSource;
import generator.sources.SimpleSeedSource;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

public class SinglePlayerGalaxyState extends BaseAppState {

	private static final Logger logger = getLogger(SinglePlayerGalaxyState.class);

	private Entity player;
	private Context galaxy;

	private final GameConfig gameConfig;

	public SinglePlayerGalaxyState(GameConfig gameConfig) {
		this.gameConfig = gameConfig;
	}

	@Override
	protected void initialize(Application app) {
		logger.info("Initializing galaxy context state");
		logger.info("game config = {}", gameConfig);

		Generator random = new Generator();
		SeedSource seedSource = new SimpleSeedSource(32768, 192f);

		galaxy = new ClassicGalaxyBootstrap(gameConfig.raceCount(), gameConfig.planetsPerRace()).create();

		player = galaxy.teams().values().iterator().next();
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

	public Collection<PlanetView> playerGalaxyView() {
		return player.prop(GalaxyView.class).asCollection();
	}

	public Collection<PlanetView> playerOwnedPlanets() {
		return player.prop(GalaxyView.class).ownedPlanets();
	}
}
