package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.planet.Materials;

public class PopulationProduction implements Production {
	private final Planet planet;

	public PopulationProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void produce(GameContext context) {
		planet.growPopulation();
	}

	@Override
	public Materials cancel() {
		return new Materials();
	}
}
