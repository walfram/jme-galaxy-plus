package galaxy.production;

import galaxy.GameContext;
import galaxy.Planet;
import galaxy.Production;

public class CapitalProduction implements Production {

	private static final double CAPITAL_COST = 5.0;

	private final Planet planet;

	public CapitalProduction(Planet planet) {
		this.planet = planet;
	}

	// 1 CAP = 5 EFFORT + 1 MATERIAL

	@Override
	public void update(GameContext context) {
		double effort = planet.effort();
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
