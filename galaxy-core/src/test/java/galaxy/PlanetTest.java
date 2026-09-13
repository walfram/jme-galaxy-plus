package galaxy;

import galaxy.planet.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PlanetTest {

	@Test
	void test_planet_owner() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));

		Optional<Race> before = planet.owner();
		assertFalse(before.isPresent());

		Race race = mock(Race.class);
		planet.changeOwner(race);

		Optional<Race> after = planet.owner();
		assertTrue(after.isPresent());

		assertEquals(race, after.get());
	}

	@Test
	void test_planet_materials() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));

		assertEquals(0.0, planet.materials().value());

		planet.unloadMaterials(new Materials(100.0));
		assertEquals(100.0, planet.materials().value());
	}

	@Test
	void test_planet_industry() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));
		assertEquals(0.0, planet.industry().value());
		assertEquals(0.0, planet.capital().value());

		planet.unloadCapital(new CapitalOf(500.0));
		assertEquals(500.0, planet.industry().value());
		assertEquals(0.0, planet.capital().value());

		planet.unloadCapital(new CapitalOf(500.0));
		assertEquals(1000.0, planet.industry().value());
		assertEquals(0.0, planet.capital().value());

		planet.unloadCapital(new CapitalOf(100.0));
		assertEquals(1000.0, planet.industry().value());
		assertEquals(100.0, planet.capital().value());
	}

	@Test
	void test_planet_population() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));
		assertEquals(0.0, planet.population().value());
		assertEquals(0.0, planet.colonists().value());

		planet.unloadColonists(new ColonistsOf(10.0));
		assertEquals(80.0, planet.population().value());
		assertEquals(0.0, planet.colonists().value());

		planet.unloadColonists(new ColonistsOf(200.0));
		double totalPopulation = 210.0 * 8;
		double expectedColonists = (totalPopulation - 1000.0) / 8.0;

		assertEquals(expectedColonists, planet.colonists().value());
		assertEquals(1000.0, planet.population().value());
		assertEquals(210.0 - 125.0, planet.colonists().value());
	}

	@Test
	void test_inhabited_planet_effort() {
		Planet planet = new Planet(
				new Coordinates(1, 2), new Size(1000.0), new Resources(1000.0), new Industry(1000.0), new Population(1000.0)
		);

		Effort effort = new Effort(planet);
		assertEquals(1000.0, effort.value());
	}

	@Test
	void test_uninhabited_planet_effort() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));

		Effort effort = new Effort(planet);
		assertEquals(0.0, effort.value());
	}

}
