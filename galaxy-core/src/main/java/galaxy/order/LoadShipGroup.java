package galaxy.order;

import galaxy.Cargo;
import galaxy.context.GameContext;
import galaxy.Order;
import galaxy.Race;
import galaxy.planet.CapitalOf;
import galaxy.planet.ColonistsOf;
import galaxy.planet.Materials;
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
			throw new IllegalArgumentException("Ship group %s does not belong to race %s".formatted(shipGroup.shipGroupId(), race.raceId()));
		}

		// TODO
		// if (!shipGroup.isInOrbit()) {
		//	throw new IllegalStateException("Ship group %s is not in orbit".formatted(shipGroup.shipGroupId()));
		// }

		if (shipGroup.planet().owner().isEmpty()) {
			throw new IllegalStateException("Planet %s is uninhabited, cannot load ship group".formatted(shipGroup.planet().planetId()));
		}

		if (!Objects.equals(shipGroup.planet().owner().get(), race)) {
			throw new IllegalStateException("Planet %s is not owned by %s, cannot load ship group".formatted(shipGroup.planet().planetId(), race.raceId()));
		}

		// TODO refactor
		switch (cargo.getClass().getSimpleName()) {
			case "ColonistsOf" -> loadColonists();
			case "CapitalOf" -> loadCapital();
			case "Materials" -> loadMaterials();
		}
	}

	private void loadMaterials() {
		double quantity = Math.min(shipGroup.planet().materials().quantity(), cargo.quantity());

		if (quantity == 0)
			throw new IllegalStateException("No materials available to load");

		shipGroup.planet().materials().withdraw(quantity);
		shipGroup.load(new Materials(quantity));
	}

	private void loadCapital() {
		double quantity = Math.min(shipGroup.planet().capital().quantity(), cargo.quantity());

		if (quantity == 0)
			throw new IllegalStateException("No capital available to load");

		shipGroup.planet().withdrawCapital(quantity);
		shipGroup.load(new CapitalOf(quantity));
	}

	private void loadColonists() {
		double quantity = Math.min(shipGroup.planet().colonists().quantity(), cargo.quantity());

		if (quantity == 0)
			throw new IllegalStateException("No colonists available to load");

		shipGroup.planet().withdrawColonists(quantity);
		shipGroup.load(new ColonistsOf(quantity));
	}
}
