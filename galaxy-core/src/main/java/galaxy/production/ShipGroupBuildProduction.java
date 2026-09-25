package galaxy.production;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.context.Planets;
import galaxy.planet.Materials;
import galaxy.planet.PlanetId;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

public final class ShipGroupBuildProduction implements Production {

	private static final double cost = 10.0;

	private final Planet planet;
	private final ShipType shipType;

	private double massFromPrevTurn;

	public ShipGroupBuildProduction(Planet planet, ShipType shipType) {
		this.planet = planet;
		this.shipType = shipType;
	}

	public ShipGroupBuildProduction(JsonNode src, Planets planets) {
		this(
				planets.findById(new PlanetId(src)),
				planets.findById(new PlanetId(src)).owner().orElseThrow().shipType(src.required("target").asText())
		);
	}

	@Override
	public void produce(GameContext context) {
		double mat = planet.materials().quantity();
		double effort = planet.effort().value();

		double produced;
		double extra = effort - mat * cost;
		if (extra > 0.0) {
			double res = planet.resources().value();
			produced = mat + extra * res / (cost * res + 1.0);
			planet.withdrawMaterials(new Materials(mat));
		} else {
			produced = effort / cost;
			planet.withdrawMaterials(new Materials(produced));
		}

		produced += massFromPrevTurn;

		double shipMass = shipType.mass();
		int count = (int) Math.floor(produced / shipMass);
		massFromPrevTurn = Math.max(0.0, produced - shipMass * (double) count);

		if (count > 0) {
			ShipGroup shipGroup = new ShipGroup(planet.owner().orElseThrow(), shipType, count);
			context.shipGroups().orbit(shipGroup, planet);
		}

	}

	@Override
	public Materials cancel() {
		return new Materials(massFromPrevTurn);
	}
}
