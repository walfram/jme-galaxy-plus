package galaxy.planet;

import galaxy.Id;
import java.util.List;
import java.util.Random;

public final class RandomRaceSelection implements RaceSelection {

	private final Random random;

	public RandomRaceSelection(final Random random) {
		this.random = random;
	}

	@Override
	public String winner(final List<String> candidates) {
		return candidates.get(this.random.nextInt(candidates.size()));
	}
}
