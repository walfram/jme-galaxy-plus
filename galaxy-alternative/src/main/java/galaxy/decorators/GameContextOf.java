package galaxy.decorators;

import galaxy.*;

public class GameContextOf implements GameContext {
	private final GameContext source;

	public GameContextOf(GameContext source) {
		this.source = source;
	}

	@Override
	public Factions factions() {
		return source.factions();
	}

	@Override
	public Planets planets() {
		return source.planets();
	}

	@Override
	public ShipGroups shipGroups() {
		return source.shipGroups();
	}

	@Override
	public Productions productions() {
		return source.productions();
	}
}
