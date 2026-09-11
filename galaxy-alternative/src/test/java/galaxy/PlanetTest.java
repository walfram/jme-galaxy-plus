package galaxy;

import galaxy.decorators.PlanetOf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlanetTest {

	@Test
	void should_update_population() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0, 1000.0, 1000.0, 5000.0, "WD-040", null);
		assertEquals(1000.0, planet.population());

		Planet updated = planet.withPopulation(2000.0);
		assertEquals(2000.0, updated.population());
	}

	@Test
	void should_update_owner() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0, 1000.0, 1000.0, 5000.0, "WD-040", null);
		assertNull(planet.owner());

		Planet updated = planet.withOwnerId("test");
		assertEquals("test", updated.owner().orElseThrow());
	}

}
