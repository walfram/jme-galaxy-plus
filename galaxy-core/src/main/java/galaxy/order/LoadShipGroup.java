package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import galaxy.Race;
import galaxy.planet.Materials;
import galaxy.ship.CargoLoad;
import galaxy.ship.CargoType;
import galaxy.ship.ShipGroup;

import java.util.Objects;

public final class LoadShipGroup implements Order {
	private final Race race;
	private final ShipGroup shipGroup;
	private final CargoLoad cargoLoad;

	public LoadShipGroup(Race race, ShipGroup shipGroup, CargoLoad cargoLoad) {
		this.race = race;
		this.shipGroup = shipGroup;
		this.cargoLoad = cargoLoad;
	}

	@Override
	public void applyTo(GameContext context) {
		if (!Objects.equals(shipGroup.race(), race)) {
			throw new IllegalArgumentException("Ship group %s does not belong to race %s".formatted(shipGroup.shipGroupId(), race.raceId()));
		}

		if (!shipGroup.isInOrbit()) {
			throw new IllegalStateException("Ship group %s is not in orbit".formatted(shipGroup.shipGroupId()));
		}

		if (shipGroup.planet().owner().isEmpty()) {
			throw new IllegalStateException("Planet %s is uninhabited, cannot load ship group".formatted(shipGroup.planet().planetId()));
		}

		if (!Objects.equals(shipGroup.planet().owner().get(), race)) {
			throw new IllegalStateException("Planet %s is not owned by %s, cannot load ship group".formatted(shipGroup.planet().planetId(), race.raceId()));
		}

		switch(cargoLoad.cargoType()) {
			case COLONISTS -> loadColonists();
			case CAPITAL -> loadCapital();
			case MATERIALS -> loadMaterials();
		}
	}

	private void loadMaterials() {
		double quantity = Math.min(shipGroup.planet().materials().value(), cargoLoad.quantity());
		shipGroup.planet().materials().withdraw(quantity);
		shipGroup.load(new CargoLoad(CargoType.MATERIALS, quantity));
	}

	private void loadCapital() {
		double quantity = Math.min(shipGroup.planet().capital().value(), cargoLoad.quantity());
		shipGroup.planet().withdrawCapital(quantity);
		shipGroup.load(new CargoLoad(CargoType.CAPITAL, quantity));
	}

	private void loadColonists() {
		double quantity = Math.min(shipGroup.planet().colonists().value(), cargoLoad.quantity());
		shipGroup.planet().withdrawColonists(quantity);
		shipGroup.load(new CargoLoad(CargoType.COLONISTS, quantity));
	}
}
