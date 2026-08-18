package galaxy.dev;

import galaxy.core.GameState;
import galaxy.phase.Phase;
import galaxy.phase.PhaseExecution;
import galaxy.phase.impl.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PhaseTest {

	@Test
	void should_execute_all_phases() {
		List<Phase> phases = List.of(
				new QuitBySleepPhase(),
				new QuitByPlanetLossPhase(),
				new ShipTransferPhase(),
				new ShipJoinPhase(),
				new FightPhase(),
				new LoadPhase(),
				new InterceptPhase(),
				new ShipMovePhase(),
				new ShipJoinPhase(),
				new FightPhase(),
				new BombingPhase(),
				new EndUpgradePhase(),
				new ProductionPhase(),
				new UnloadPhase(),
				new ShipJoinPhase(),
				new VictoryCheckPhase()
		);

		GameState gameState = mock(GameState.class);

		PhaseExecution phaseExecution = new PhaseExecution(phases);
		int executed = phaseExecution.execute(gameState);

		assertEquals(phases.size(), executed);
	}
}
