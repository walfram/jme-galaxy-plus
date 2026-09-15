package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import galaxy.Race;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

import java.util.Optional;

public final class DefineShipType implements Order {
	private final Race race;
	private final ShipType shipType;

	public DefineShipType(Race race, ShipType shipType) {
		this.race = race;
		this.shipType = shipType;
	}

	@Override
	public void applyTo(GameContext context) {
		Optional<ShipGroup> byOwnerAndName = context.shipGroups().findByOwnerAndName(race.raceId(), shipType.name());

		if (byOwnerAndName.isPresent()) {
			throw new IllegalStateException("Cannot define ship type %s for race %s, ship group already exists".formatted(shipType.name(), race.raceId()));
		}

		race.shipTypes().add(shipType);
	}
}
