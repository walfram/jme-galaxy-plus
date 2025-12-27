package domain.phase;

import domain.Context;
import domain.Entity;
import domain.Phase;
import domain.planet.*;
import org.slf4j.Logger;

import java.util.List;

import static domain.Const.COLONISTS_RATIO;
import static org.slf4j.LoggerFactory.getLogger;

public class PopulationGrowthPhase implements Phase {

	private static final Logger logger = getLogger(PopulationGrowthPhase.class);

	private final Context galaxy;

	public PopulationGrowthPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> populatedPlanets = galaxy.query(List.of(
				PlanetRef.class,
				Planet.class,
				Population.class
		));
		logger.debug("populated planets {}", populatedPlanets.size());

		for (Entity planet : populatedPlanets) {
			double currentPopulation = planet.prop(Population.class).value();

			if (currentPopulation == 0) {
				logger.debug("population of {} is zero, skipping growth", planet);
				continue;
			}

			double maxPopulation = planet.prop(Size.class).value();

			double delta = currentPopulation * 0.08;
			logger.debug("growing population of {} by {}%", planet, delta);

			double updatedPopulation = currentPopulation + delta;

			if (maxPopulation - updatedPopulation < 0.0) {
				planet.put(new Population(maxPopulation));
				double colonists = planet.has(Colonists.class) ? planet.prop(Colonists.class).value() : 0.0;
				colonists += (updatedPopulation - maxPopulation) / COLONISTS_RATIO;
				planet.put(new Colonists(colonists));
			} else {
				planet.put(new Population(updatedPopulation));
			}
		}
	}
}
