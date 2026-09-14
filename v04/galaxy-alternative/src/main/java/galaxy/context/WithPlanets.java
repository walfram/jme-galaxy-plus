package galaxy.context;

import galaxy.*;

public final class WithPlanets implements GameContext {

	private final GameContext origin;
	private final Planets planets;

	public WithPlanets(GameContext origin, Planets planets) {
		this.origin = origin;
		this.planets = planets;
	}

	@Override
	public Factions factions() {
		return origin.factions();
	}

	@Override
	public Planets planets() {
		return planets;
	}

	@Override
	public ShipGroups shipGroups() {
		return origin.shipGroups();
	}

	@Override
	public Productions productions() {
		return origin.productions();
	}
}
