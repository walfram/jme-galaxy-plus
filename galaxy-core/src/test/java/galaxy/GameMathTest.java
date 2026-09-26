package galaxy;

import galaxy.context.GameContext;
import galaxy.planet.Colonists;
import galaxy.planet.Coordinates;
import galaxy.planet.Resources;
import galaxy.planet.Size;
import galaxy.production.PopulationGrowProduction;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.mock;

public class GameMathTest {

	private static final Logger logger = LoggerFactory.getLogger(GameMathTest.class);

	private static final double[] sizes = {100.0, 250.0, 500.0, 750.0, 1000.0, 1500.0, 2000.0, 2500.0};
	private static final double[] resources = {0.1, 0.5, 1.0, 2.5, 5.0, 10.0, 15.0, 20.0, 25.0};

	@Test
	void test_capital_per_turn() {
		double res = 10.0;
		for (double effort : sizes) {
			double mat = effort / 5.0 - 1.0;
			double capNoMat = (effort + mat / res) / (5.0 + 1.0 / res);
			double capWithMat = (effort / 5.0);
			logger.info("planet's size and effort = {}, mat = {}, capNoMat = {}, capWithMat = {}", effort, mat, capNoMat, capWithMat);
		}
	}

	@Test
	void test_cap_production_turns() {
		for (double res : resources) {
			double t = (20.0 + 4.0 / res) / 3.0;
			logger.info("planet's resources = {}, cap production turns = {}", res, t);
		}
	}

	@Test
	void test_planet_population_growth() {
		GameContext context = mock(GameContext.class);
		Race race = mock(Race.class);

		for (double size : sizes) {
			Planet planet = new Planet(new Coordinates(0, 0), new Size(size), new Resources(10.0));
			planet.changeOwner(race);

			planet.unloadColonists(new Colonists(1.0));

			int turns = 0;
			Production production = new PopulationGrowProduction(planet);

			while (planet.population().value() < size) {
				logger.info("size = {}, turn = {}, population = {}", size, turns, planet.population().value());
				production.produce(context);
				turns++;
			}

			logger.info("Planet population growth for size {} took {} turns", size, turns);
		}
	}

}
