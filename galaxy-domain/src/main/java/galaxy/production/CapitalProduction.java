package galaxy.production;

import galaxy.GameContext;
import galaxy.planet.Planet;
import galaxy.Production;
import galaxy.planet.properties.CapitalStockpile;
import galaxy.planet.properties.MaterialsStockpile;

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
		double materials = planet.property(MaterialsStockpile.class).map(MaterialsStockpile::value).orElse(0.0);

		double extra = effort - materials * CAPITAL_COST;

		double produced;
		if (extra >= 0.0) {
			double resources = planet.resources();
			produced = materials + extra * resources / (CAPITAL_COST * resources + 1.0);
//			planet.materials().update(-materials);
			planet.putProperty(new MaterialsStockpile(0.0)); // ???
		} else {
			produced = effort / CAPITAL_COST;
//			planet.materials().update(-produced);
			planet.putProperty(new MaterialsStockpile(materials - produced));
		}

		planet.putProperty(new CapitalStockpile(produced));
	}
}
