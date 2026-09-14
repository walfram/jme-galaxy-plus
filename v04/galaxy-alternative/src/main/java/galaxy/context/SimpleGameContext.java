package galaxy.context;

import galaxy.*;

public final class SimpleGameContext implements GameContext {

	private final Factions factions;
	private final Planets planets;
	private final ShipGroups shipGroups;
	private final Productions productions;

	public SimpleGameContext(Factions factions, Planets planets, ShipGroups shipGroups, Productions productions) {
		this.factions = factions;
		this.planets = planets;
		this.shipGroups = shipGroups;
		this.productions = productions;
	}

	@Override
	public Factions factions() {
		return factions;
	}

	@Override
	public Planets planets() {
		return planets;
	}

	@Override
	public ShipGroups shipGroups() {
		return shipGroups;
	}

	@Override
	public Productions productions() {
		return productions;
	}
}
