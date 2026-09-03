package galaxy.production;

import galaxy.GameContext;
import galaxy.Planet;
import galaxy.Production;

public class PopulationGrowthProduction implements Production {

	private static final double POPULATION_GROWTH_RATIO = 0.08;

	private final Planet planet;

	public PopulationGrowthProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void update(GameContext context) {
		double population = planet.population().value();
		double growth = POPULATION_GROWTH_RATIO * population;
		planet.updatePopulation(growth);
	}

}
