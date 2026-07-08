package galaxy.core.production;

import galaxy.core.planet.Effort;
import galaxy.core.planet.MassFromPreviousTurn;
import galaxy.core.planet.Materials;
import galaxy.core.planet.Resources;
import galaxy.core.production.result.SpawnShips;
import galaxy.core.ship.ShipDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShipProduction implements Production {

	private static final double SHIP_COST = 10.0;

	private final ShipDesign design;

	public ShipProduction(ShipDesign design) {
		this.design = design;
	}

	@Override
	public void advance(double tpf) {
	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public List<ProductionResult> complete(ProductionContext context) {
		double effort = new Effort(context.planet()).value();
		double materials = Optional.ofNullable(context.planet().prop(Materials.class)).map(Materials::value).orElse(0.0);

		double extra = effort - materials * SHIP_COST;

		double produced;
		double materialsDelta;

		if (extra > 0.0) {
			double resources = context.planet().prop(Resources.class).value();
			produced = materials + extra * resources / (SHIP_COST * resources + 1.0);
			materialsDelta = -materials;
		} else {
			produced = effort / SHIP_COST;
			materialsDelta = -produced;
		}

		Materials materialsComponent = new Materials(materials + materialsDelta);
		context.planet().put(materialsComponent);

		produced += Optional.ofNullable(context.planet().prop(MassFromPreviousTurn.class)).map(MassFromPreviousTurn::value).orElse(0.0);

		double shipMass = design.weight();
		int count = (int) Math.floor(produced / shipMass);

		List<ProductionResult> response = new ArrayList<>();

		if (count > 0) {
			response.add(new SpawnShips(count, design, context.planet()));
		}

		double remainder = produced - shipMass * (double) count;
		context.planet().put(new MassFromPreviousTurn(Math.max(0.0, remainder)));

		return response;
	}
}
