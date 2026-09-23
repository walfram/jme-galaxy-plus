package legacy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LegacyPlanetTest {

	@Test
	void should_grow_population() {
		LegacyPlanet planet = new LegacyPlanet(1000.0);
		assertEquals(0.0, planet.population());
		assertEquals(0.0, planet.colonists());

		planet.growPopulation();
		assertEquals(0.0, planet.population());
		assertEquals(0.0, planet.colonists());

		planet.unloadColonists(1.0);
		assertEquals(8.0, planet.population());
		assertEquals(0.0, planet.colonists());

		planet.unloadColonists(125.0);
		assertEquals(1000.0, planet.population());
		assertEquals(1.0, planet.colonists());

		double removed = planet.removeColonists(1.0);
		assertEquals(1.0, removed);
		assertEquals(0.0, planet.colonists());
		assertEquals(1000.0, planet.population());
	}

}
