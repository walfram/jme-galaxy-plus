package galaxy.production;

import galaxy.*;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

public class ShipGroupProduction implements Production {
	private final Race race;
	private final Planet planet;
	private final ShipType shipType;

	public ShipGroupProduction(Race race, Planet planet, ShipType shipType) {
		this.race = race;
		this.planet = planet;
		this.shipType = shipType;
	}

	public ShipType shipType() {
		return shipType;
	}

	@Override
	public void update(GameContext context) {
		// TODO calculate size and other stuff

		int size = 32;

		ShipGroup group = new ShipGroup(race, shipType, size);

		context.createShipGroup(race, planet, group);
	}
}
