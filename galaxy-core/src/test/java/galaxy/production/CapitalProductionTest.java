package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.planet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CapitalProductionTest {

	@Test
	void test_produce_capital() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(10.0),
				new PopulationOf(1000.0),
				new IndustryOf(1000.0)
		);

		GameContext context = mock(GameContext.class);

		Production production = new CapitalProduction(planet);
		production.produce(context);

		assertEquals(196.07843137254895, planet.capital().quantity());
	}

}
