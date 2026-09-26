package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.Race;
import galaxy.Tech;
import galaxy.context.GameContext;
import galaxy.planet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ResearchTechProductionTest {

	@Test
	void test_produce_tech() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(10.0),
				new PopulationOf(1000.0),
				new IndustryOf(1000.0)
		);

		Race race = new Race("test");
		planet.changeOwner(race);

		GameContext context = mock(GameContext.class);

		Production production = new ResearchTechProduction(planet, Tech.ENGINES);
		production.produce(context);

		assertEquals(1.2, race.techLevels().engines().value());
	}

}
