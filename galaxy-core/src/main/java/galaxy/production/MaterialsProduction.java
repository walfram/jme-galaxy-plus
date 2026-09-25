package galaxy.production;

import galaxy.Effort;
import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.planet.Materials;

public final class MaterialsProduction implements Production {
	private final Planet planet;

	public MaterialsProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void produce(GameContext context) {
		double effort = planet.effort().value();
		double resources = planet.resources().value();
		double produced = effort * resources;

		planet.unloadMaterials(new Materials(produced));
	}

	@Override
	public Materials cancel() {
		return new Materials();
	}
}
