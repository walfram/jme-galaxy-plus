package domain.phase;

import domain.Context;
import domain.Entity;
import domain.Phase;
import domain.planet.Planet;
import domain.planet.PlanetRef;
import domain.planet.Population;
import org.slf4j.Logger;

import java.util.List;

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
			double value = planet.prop(Population.class).value();

			if (value == 0) {
				logger.debug("population of {} is zero, skipping growth", planet);
				continue;
			}

			double delta = value * 0.08;
			logger.debug("growing population of {} by {}%", planet, delta);
			planet.put(new Population(value + delta));
		}
	}
}
