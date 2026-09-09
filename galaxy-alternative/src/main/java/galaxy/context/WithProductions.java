package galaxy.context;

import galaxy.*;

public class WithProductions implements GameContext {

	private final GameContext origin;
	private final Productions productions;

	public WithProductions(GameContext origin, Productions productions) {
		this.origin = origin;
		this.productions = productions;
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
		return origin.shipGroups();
	}

	@Override
	public Productions productions() {
		return productions;
	}
}
