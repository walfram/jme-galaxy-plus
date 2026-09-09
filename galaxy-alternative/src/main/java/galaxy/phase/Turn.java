package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;

import java.util.Arrays;
import java.util.List;

public final class Turn implements Phase {

	private final List<Phase> phases;

	public Turn(final Phase... phases) {
		this(Arrays.asList(phases));
	}

	public Turn(final List<Phase> phases) {
		this.phases = phases;
	}

	@Override
	public GameContext process(final GameContext context) {
		return this.phases.stream().reduce(
				context,
				(ctx, phase) -> phase.process(ctx),
				(a, b) -> b
		);
	}

}
