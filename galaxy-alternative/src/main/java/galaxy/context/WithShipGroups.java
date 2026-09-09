package galaxy.context;

import galaxy.*;

public final class WithShipGroups implements GameContext {

	private final GameContext origin;
	private final ShipGroups shipGroups;

	public WithShipGroups(GameContext origin, ShipGroups shipGroups) {
		this.origin = origin;
		this.shipGroups = shipGroups;
	}

	@Override
	public Factions factions() {
		return origin.factions();
	}

	@Override
	public Planets planets() {
		return origin.planets();
	}

	@Override
	public ShipGroups shipGroups() {
		return shipGroups;
	}

	@Override
	public Productions productions() {
		return origin.productions();
	}
}
