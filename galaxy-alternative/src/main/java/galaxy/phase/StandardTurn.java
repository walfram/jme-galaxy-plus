package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;

public final class StandardTurn implements Phase {
	private final Phase origin;

	public StandardTurn() {
		this.origin = new Turn(
				new QuitBySleep(),
				new QuitByNoPlanets(),
				new ShipTransfer(),
				new JoinShipGroups(),
				new CombatPhase(),
				new LoadCargo(),
				new MovePhase(),
				new JoinShipGroups(),
				new CombatPhase(),
				new BombingPhase(),
				new ProductionPhase(),
				new UnloadPhase(),
				new JoinShipGroups(),
				new VictoryCheck()
		);
	}

	@Override
	public GameContext process(final GameContext context) {
		return this.origin.process(context);
	}
}
