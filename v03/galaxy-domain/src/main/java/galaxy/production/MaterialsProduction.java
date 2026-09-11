package galaxy.production;

import galaxy.GameContext;
import galaxy.Planet;
import galaxy.Production;

public class MaterialsProduction implements Production {

	private final Planet planet;

	public MaterialsProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void update(GameContext context) {
		double effort = planet.effort();
		double resources = planet.resources().value();

		planet.updateMaterials(effort * resources);
	}

}
