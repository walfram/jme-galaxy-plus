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

		assertEquals(0.0, planet.materials().quantity());

		planet.unloadMaterials(new Materials(100.0));
		assertEquals(100.0, planet.materials().quantity());
	}

	@Test
	void test_planet_industry() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0), new PopulationOf(1000.0));
		assertEquals(0.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unloadCapital(new Capital(500.0));
		assertEquals(500.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unloadCapital(new Capital(500.0));
		assertEquals(1000.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unloadCapital(new Capital(100.0));
		assertEquals(1000.0, planet.industry().value());
		assertEquals(100.0, planet.capital().quantity());
	}

	@Test
	void test_planet_population() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));
		assertEquals(0.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		planet.unloadColonists(new Colonists(10.0));
		assertEquals(80.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		planet.unloadColonists(new Colonists(200.0));
		double totalPopulation = 210.0 * 8;
		double expectedColonists = (totalPopulation - 1000.0) / 8.0;

		assertEquals(expectedColonists, planet.colonists().quantity());
		assertEquals(1000.0, planet.population().value());
		assertEquals(210.0 - 125.0, planet.colonists().quantity());
	}

	@Test
	void test_inhabited_planet_effort() {
		Planet planet = new Planet(
				new Coordinates(1, 2), new Size(1000.0), new Resources(1000.0), new PopulationOf(1000.0), new IndustryOf(1000.0)
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

	@Test
	void test_industry_cannot_be_greater_then_population() {
		Planet planet = new Planet(
				new Coordinates(2, 3),
				new Size(1000.0),
				new Resources(10.0),
				new PopulationOf(800.0),
				new IndustryOf(1000.0)
		);

		assertEquals(800.0, planet.industry().value());
		assertEquals(800.0, planet.population().value());
		assertEquals(200.0, planet.capital().quantity());

		planet.unloadColonists(new Colonists(200.0 / 8.0));
		assertEquals(1000.0, planet.industry().value());
		assertEquals(1000.0, planet.population().value());
		assertEquals(0.0, planet.capital().quantity());
	}

	@Test
	void should_level_up_industry() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));

		planet.unloadColonists(new Colonists(200.0));

		assertEquals(0.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unloadCapital(new Capital(500.0));

		assertEquals(500.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unloadCapital(new Capital(500.0));

		assertEquals(1000.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unloadCapital(new Capital(1000.0));

		assertEquals(1000.0, planet.industry().value());
		assertEquals(1000.0, planet.capital().quantity());
	}

	@Test
	void should_level_up_population() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(10.0)
		);

		assertEquals(0.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		planet.unloadColonists(new Colonists(1.0));
		assertEquals(8.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		double c = 1000.0 / 8.0;
		planet.unloadColonists(new Colonists(c));
		assertEquals(1000.0, planet.population().value());
		assertEquals(1.0, planet.colonists().quantity());
	}

	@Test
	void should_grow_population() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(10.0),
				new PopulationOf(1000.0),
				new IndustryOf(1000.0)
		);

		assertEquals(1000.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		planet.growPopulation();
		assertEquals(1000.0, planet.population().value());
		assertEquals(10.0, planet.colonists().quantity());
	}

}
