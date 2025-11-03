package galaxy.domain.production;

import galaxy.domain.planet.Planet;

public class CapitalProduction extends Production {

	// TODO GameSettings or similar
	private static final double CAPITAL_COST = 5.0;

	public CapitalProduction(Planet planet) {
		super(planet);
	}

	// @see opengs CAP_Production, docs not clear about this
	@Override
	public void execute() {
		double effort = planet.effort().value();
		double materials = planet.materials().value();

		double extra = effort - materials * CAPITAL_COST;

		double produced;
		if (extra >= 0.0) {
			double resources = planet.resources().value();
			produced = materials + extra * resources / (CAPITAL_COST * resources + 1.0);
			planet.materials().update(-materials);
		} else {
			produced = effort / CAPITAL_COST;
			planet.materials().update(-produced);
		}

		planet.industry().update(produced);
	}
}
