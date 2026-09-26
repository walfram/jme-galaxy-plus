package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.planet.Materials;

public class PopulationGrowProduction implements Production {
	private final Planet planet;

	public PopulationGrowProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void produce(GameContext context) {
		if (planet.owner().isEmpty())
			throw new IllegalStateException("Cannot grow population on %s - planet has no owner".formatted(planet.planetId()));

		if (planet.population().value() == 0)
			throw new IllegalStateException("Cannot grow population on %s - planet has no population".formatted(planet.planetId()));

		planet.growPopulation();
	}

	@Override
	public Materials cancel() {
		return new Materials();
	}
}
