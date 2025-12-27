package domain.production;

import domain.Race;
import domain.planet.Planet;
import domain.ship.ShipTemplate;

public class ShipProduction extends Production {

	// TODO GameSettings or similar
	private static final double SHIP_COST = 10.0;

	private final Race race;
	private final ShipTemplate shipTemplate;

	public ShipProduction(Race race, Planet planet, ShipTemplate shipTemplate) {
		super(planet);
		this.race = race;
		this.shipTemplate = shipTemplate;
	}

	// @see opengs Ship_Production
	@Override
	public void execute() {
		double effort = planet.effort().value();
		double materials = planet.materials().value();

		double extra = effort - materials * SHIP_COST;

		double produced;
		if (extra > 0.0) {
			double resources = planet.resources().value();
			produced = materials + extra * resources / (SHIP_COST * resources + 1.0);
			planet.materials().update(-materials);
		} else {
			produced = effort / SHIP_COST;
			planet.materials().update(-produced);
		}

		produced += planet.massFromPrevTurn();

		double shipMass = shipTemplate.weight();
		int count = (int) Math.floor(produced / shipMass);

		if (count > 0) {
			// TODO add ships not templates, bind current tech level (and template?)
			race.addShipGroup(count, shipTemplate, planet);
		}

		planet.setMassFromPrevTurn(Math.max(0.0, produced - shipMass * (double) count));
	}
}
