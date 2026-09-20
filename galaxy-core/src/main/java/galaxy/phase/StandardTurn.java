package galaxy.phase;

import galaxy.context.GameContext;
import galaxy.Phase;

import java.util.List;

public class StandardTurn implements Phase {

	private final List<Phase> phases;

	public StandardTurn() {
		this(
				List.of(
						new QuitBySleep(),
						new QuitByNoPlanets(),
						new ShipTransfer(),
						new JoinShipGroups(),
						new CombatPhase(),
						new LoadCargo(),
						new SendShipGroupsPhase(),
						new MovePhase(),
						new JoinShipGroups(),
						new CombatPhase(),
						new BombingPhase(),
						new ProductionPhase(),
						new UnloadPhase(),
						new JoinShipGroups(),
						new VictoryCheck()
				)
		);
	}

	public StandardTurn(List<Phase> phases) {
		this.phases = phases;
	}

	@Override
	public GameContext execute(GameContext context) {
		GameContext processed = context;

		for (Phase phase : phases) {
			processed = phase.execute(context);
		}

		return processed;
	}
}
