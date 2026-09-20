package galaxy.order;

import galaxy.*;
import galaxy.context.GameContext;

import java.util.Optional;

public final class DefineScience implements Order {
	private final Race race;
	private final Science science;

	public DefineScience(Race race, Science science) {
		this.race = race;
		this.science = science;
	}

	@Override
	public void applyTo(GameContext context) {
		Optional<Production> byOwnerAndName = context.productions().findByOwnerAndName(race, science.productionName());

		if (byOwnerAndName.isPresent()) {
			throw new IllegalStateException("Cannot define science %s for race %s, science is being researched".formatted(science.productionName(), race.raceId()));
		}

		race.sciences().add(science);
	}
}
