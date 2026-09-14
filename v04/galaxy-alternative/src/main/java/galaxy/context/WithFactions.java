package galaxy.context;

import galaxy.*;

public final class WithFactions implements GameContext {

	private final GameContext origin;
	private final Factions factions;

	public WithFactions(GameContext origin, Factions factions) {
		this.origin = origin;
		this.factions = factions;
	}

	@Override
	public Factions factions() {
		return factions;
	}

	@Override
	public Planets planets() {
		return origin.planets();
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
