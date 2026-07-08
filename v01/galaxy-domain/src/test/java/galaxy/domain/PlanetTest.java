package galaxy.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlanetTest {

	@Test
	void test_unloading_cap_increases_industry() {
		Planet planet = new Planet(new PlanetId(1), new Size(1000), new Resources(10), new Coordinates(1, 2, 3), new Population(1000f), new Industry(500f));

		assertEquals(0f, planet.capital());

		planet.updateCapital(1000f);

		assertEquals(1000f, planet.industry());
		assertEquals(500f, planet.capital());
	}

	@Test
	void test_produce_colonists() {
		Planet planet = new Planet(new PlanetId(1), new Size(1000), new Resources(10), new Coordinates(1, 2, 3), new Population(1000f), new Industry(1000f));

		planet.updatePopulation(800f);

		assertEquals(1000f, planet.population());
		assertEquals(100f, planet.colonists());
	}

	@Test
	void test_planet_owner_change() {

	}

	@Test
	void test_planet_ships_in_orbit() {

	}

	@Test
	void test_planet_production() {

	}

	@Test
	void test_bombing_planet() {

	}

	@Test
	void should_create_uninhabited_planet() {
		Planet planet = new Planet(
				new PlanetId(1), new Size(1000), new Resources(10), new Coordinates(1, 2, 3)
		);

		assertEquals(0f, planet.population());
		assertEquals(0f, planet.materials());
		assertEquals(0f, planet.industry());
		assertEquals(0f, planet.effort());

		assertNull(planet.owner());
	}

	@Test
	void should_create_homeworld() {
		Planet planet = new Planet(
				new PlanetId(1), new Size(1000), new Resources(10), new Coordinates(1, 2, 3), new Population(1000f), new Industry(1000f)
		);

		Race race = new Race("test");
		planet.changeOwner(race);

		assertEquals(1000f, planet.population());
		assertEquals(1000f, planet.industry());
		assertEquals(0f, planet.materials());
		assertEquals(1000f, planet.effort());

		assertEquals(race, planet.owner());
	}

}
