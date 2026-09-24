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
		double effort = new Effort(planet).value();

		double resources = planet.resources().value();

		double produced = effort * resources;

		planet.materials().add(new Materials(produced));
	}
}
