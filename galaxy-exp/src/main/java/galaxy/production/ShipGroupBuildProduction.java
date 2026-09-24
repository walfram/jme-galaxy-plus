package galaxy.production;

import galaxy.planet.Materials;
import galaxy.planet.Planet;
import galaxy.ships.ShipGroup;
import galaxy.ships.ShipType;

public class ShipGroupBuildProduction implements Production<ShipGroup> {

	private static final double cost = 10.0;

	private final Planet planet;
	private final ShipType shipType;

	private double massFromPrevTurn;

	public ShipGroupBuildProduction(Planet planet, ShipType shipType) {
		this.planet = planet;
		this.shipType = shipType;
	}

	@Override
	public ShipGroup produce() {
		double mat = planet.materials().quantity();
		double effort = planet.effort().value();

		double produced;
		double extra = effort - mat * cost;
		if (extra > 0.0) {
			double res = planet.resources().value();
			produced = mat + extra * res / (cost * res + 1.0);
			planet.remove(new Materials(mat));
		} else {
			produced = effort / cost;
			planet.remove(new Materials(produced));
		}

		// produced += planet.massFromPrevTurn();
		produced += massFromPrevTurn;

		double shipMass = shipType.mass();
		int count = (int) Math.floor(produced / shipMass);
		// planet.massFromPrevTurn(Math.max(0.0, produced - shipMass * (double) count));
		massFromPrevTurn = Math.max(0.0, produced - shipMass * (double) count);
		if (count > 0) {
			// owner.addShipGroup(count, shipType, planet);
			return new ShipGroup(planet.owner().orElseThrow(), planet, shipType, count);
		}

		return null; // still producing
	}
}
