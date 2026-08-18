package galaxy.phase;

import galaxy.core.GameState;

import java.util.List;

public class PhaseExecution {
	private final List<Phase> phases;

	public PhaseExecution(List<Phase> phases) {
		this.phases = phases;
	}

	public int execute(GameState gameState) {
		int executed = 0;

		for (Phase phase : phases) {
			boolean updated = phase.update(gameState);

			if (updated) {
				executed++;
			}
		}

		return executed;
	}
}
