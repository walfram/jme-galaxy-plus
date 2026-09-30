package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.Race;
import galaxy.context.GameContext;
import galaxy.planet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PopulationGrowProductionTest {

	@Test
	void test_produce_population() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0), new PopulationOf(100.0), new IndustryOf(1000.0));

		Race race = new Race("test");
		planet.changeOwner(race);

		GameContext context = mock(GameContext.class);

		assertEquals(100.0, planet.population().value());

		Production production = new PopulationGrowProduction(planet);
		production.produce(context);

		assertEquals(108.0, planet.population().value());
	}

}
