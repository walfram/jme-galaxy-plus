package galaxy.production;

import galaxy.GameContext;
import galaxy.planet.Planet;
import galaxy.Production;
import galaxy.planet.properties.MaterialsStockpile;

public class MaterialsProduction implements Production {

	private final Planet planet;

	public MaterialsProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void update(GameContext context) {
		double effort = planet.effort();
		double resources = planet.resources();

		double m = planet.property(MaterialsStockpile.class).map(MaterialsStockpile::value).orElse(0.0);

		planet.putProperty(new MaterialsStockpile(m + effort * resources));
	}

}
