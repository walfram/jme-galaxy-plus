package galaxy.domain.production;

import galaxy.domain.Planet;

public class PopulationProduction extends Production {
	public PopulationProduction(Planet planet) {
		super(planet);
	}

	@Override
	public void execute() {
		double population = planet.population().value();
		double populationIncrease = population * 0.08;

		planet.population().update(populationIncrease);
	}
}
