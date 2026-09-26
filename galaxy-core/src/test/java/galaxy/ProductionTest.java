package galaxy;

import galaxy.context.GameContext;
import galaxy.planet.*;
import galaxy.production.MaterialsProduction;
import galaxy.production.PopulationGrowProduction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ProductionTest {

	// production requires context
	// production requires planet

	@Test
	void test_produce_population() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0), new PopulationOf(100.0), new IndustryOf(1000.0));
		GameContext context = mock(GameContext.class);

		assertEquals(100.0, planet.population().value());

		Production production = new PopulationGrowProduction(planet);
		production.produce(context);

		assertEquals(108.0, planet.population().value());
	}

	@Test
	void test_produce_materials() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0), new PopulationOf(1000.0), new IndustryOf(1000.0));
		GameContext context = mock(GameContext.class);

		assertEquals(0.0, planet.materials().quantity());

		Production production = new MaterialsProduction(planet);
		production.produce(context);

		assertEquals(10000.0, planet.materials().quantity());
	}

}
