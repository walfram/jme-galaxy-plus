package galaxy.order;

import galaxy.Order;
import galaxy.Planet;
import galaxy.Race;
import galaxy.context.GameContext;
import galaxy.ship.ShipGroup;

import java.util.Objects;
import java.util.Optional;

public final class SendShipGroup implements Order {
	private final Race race;
	private final ShipGroup shipGroup;
	private final Planet origin;
	private final Planet destination;

	public SendShipGroup(Race race, ShipGroup shipGroup, Planet origin, Planet destination) {
		this.race = race;
		this.shipGroup = shipGroup;
		this.origin = origin;
		this.destination = destination;
	}

	@Override
	public void applyTo(GameContext context) {
		if (!Objects.equals(shipGroup.owner(), race)) {
			throw new IllegalArgumentException("Ship group %s does not belong to race %s".formatted(shipGroup.shipGroupId(), race.raceId()));
		}

		Optional<Planet> check = context.shipGroups().orbitingPlanet(shipGroup);

		if (check.isEmpty()) {
			throw new IllegalStateException("Ship group %s is not in orbit of %s".formatted(shipGroup.shipGroupId(), origin.planetId()));
		}

		context.shipGroups().launch(shipGroup, destination);
	}

}
