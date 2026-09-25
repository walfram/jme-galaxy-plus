package galaxy.order;

import galaxy.Cargo;
import galaxy.Order;
import galaxy.Planet;
import galaxy.Race;
import galaxy.context.GameContext;
import galaxy.planet.*;
import galaxy.ship.ShipGroup;

import java.util.Objects;

public final class LoadShipGroup implements Order {
	private final Race race;
	private final ShipGroup shipGroup;
	private final Cargo cargo;

	public LoadShipGroup(Race race, ShipGroup shipGroup, Cargo cargo) {
		this.race = race;
		this.shipGroup = shipGroup;
		this.cargo = cargo;
	}

	@Override
	public void applyTo(GameContext context) {
		if (!Objects.equals(shipGroup.owner(), race)) {
			throw new IllegalStateException("Ship group %s does not belong to race %s".formatted(shipGroup.shipGroupId(), race.raceId()));
		}

		Planet planet = context.shipGroups().orbitingPlanet(shipGroup)
				.orElseThrow(() -> new IllegalStateException("Ship group %s is not in orbit".formatted(shipGroup.shipGroupId())));

		if (planet.owner().isEmpty()) {
			throw new IllegalStateException("Planet %s is uninhabited, cannot load ship group".formatted(planet.planetId()));
		}

		if (!Objects.equals(planet.owner().get(), race)) {
			throw new IllegalStateException("Planet %s is not owned by %s, cannot load ship group".formatted(planet.planetId(), race.raceId()));
		}

		switch (cargo) {
			case Colonists col -> loadColonists(planet);
			case Capital cap -> loadCapital(planet);
			case Materials mat -> loadMaterials(planet);
			default -> throw new IllegalStateException("Unknown cargo: %s".formatted(cargo));
		}
	}

	private void loadMaterials(Planet planet) {
		double quantity = Math.min(planet.materials().quantity(), cargo.quantity());

		if (quantity == 0)
			throw new IllegalStateException("No materials available to load");

		Materials materials = planet.materials().remove(quantity);
		shipGroup.load(materials);
	}

	private void loadCapital(Planet planet) {
		double quantity = Math.min(planet.capital().quantity(), cargo.quantity());

		if (quantity == 0)
			throw new IllegalStateException("No capital available to load");

		Capital capital = planet.withdrawCapital(quantity);
		shipGroup.load(capital);
	}

	private void loadColonists(Planet planet) {
//		double quantity = Math.min(planet.colonists().quantity(), cargo.quantity());

//		if (quantity == 0)
//			throw new IllegalStateException("No colonists available to load");

		Colonists colonists = planet.withdrawColonists(cargo.quantity());
		shipGroup.load(colonists);
	}
}
