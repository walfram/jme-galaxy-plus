package galaxy.production;

import galaxy.GameContext;
import galaxy.planet.Planet;
import galaxy.Production;
import galaxy.planet.properties.ColonistsStockpile;
import galaxy.planet.properties.Population;

public class PopulationGrowthProduction implements Production {

	private static final double POPULATION_GROWTH_RATIO = 0.08;
	private static final double COLONISTS_PACK_RATIO = 8.0;

	private final Planet planet;

	public PopulationGrowthProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void update(GameContext context) {
		double population = planet.property(Population.class).map(Population::value).orElse(0.0);
		double growth = POPULATION_GROWTH_RATIO * population;

		// add growth to population, convert excess to colonists
		population += growth;

		double size = planet.size();

		if (population > size) {
			double colonists = (population - size) / COLONISTS_PACK_RATIO;
			planet.putProperty(new Population(size));
			planet.putProperty(new ColonistsStockpile(colonists));
		} else {
			planet.putProperty(new Population(population));
		}
	}
}
