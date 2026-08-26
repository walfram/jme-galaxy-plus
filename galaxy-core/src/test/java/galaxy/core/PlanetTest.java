package galaxy.core;

import galaxy.core.planet.Industry;
import galaxy.core.planet.Population;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

	private Planet planet;

	private static class TestProduction implements Production {}

	@BeforeEach
	void setup() {
		planet = new Planet(UUID.randomUUID().toString(), 32.1, 34.5, 1500.0, 8.0);
	}

	@Test
	void should_start_production_if_planet_is_inhabited() {
		Race race = new Race("test-race");
		planet.changeOwner(race);
		assertDoesNotThrow(() -> planet.startProduction(new TestProduction()));
	}

	@Test
	void should_not_start_production_if_planet_is_uninhabited() {
		boolean started = planet.startProduction(new TestProduction());
		assertFalse(started);
	}

	@Test
	void should_create_uninhabited_planet() {
		assertEquals(32.1, planet.x());
		assertEquals(34.5, planet.y());
		assertEquals(1500.0, planet.size());
		assertEquals(8.0, planet.resources());

		Optional<Population> population = planet.property(Population.class);
		assertFalse(population.isPresent());

		Optional<Industry> industry = planet.property(Industry.class);
		assertFalse(industry.isPresent());

		assertEquals(0.0, planet.effort());

		Optional<Production> production = planet.production();
		assertFalse(production.isPresent());
	}

}
