package galaxy.planet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanetTest {

	@Test
	void test_industry_cannot_be_greater_then_population() {
		Planet planet = new Planet(
				new Coordinates(2, 3),
				new Size(1000.0),
				new Resources(10.0),
				new IndustryOf(1000.0),
				new PopulationOf(800.0)
		);

		assertEquals(800.0, planet.industry().value());
		assertEquals(800.0, planet.population().value());
		assertEquals(200.0, planet.capital().quantity());

		planet.unload(new Colonists(200.0 / 8.0));
		assertEquals(1000.0, planet.industry().value());
		assertEquals(1000.0, planet.population().value());
		assertEquals(0.0, planet.capital().quantity());
	}

	@Test
	void should_level_up_industry() {
		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0));

		planet.unload(new Colonists(200.0));

		assertEquals(0.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unload(new Capital(500.0));

		assertEquals(500.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unload(new Capital(500.0));

		assertEquals(1000.0, planet.industry().value());
		assertEquals(0.0, planet.capital().quantity());

		planet.unload(new Capital(1000.0));

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

		planet.unload(new Colonists(1.0));
		assertEquals(8.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		double c = 1000.0 / 8.0;
		planet.unload(new Colonists(c));
		assertEquals(1000.0, planet.population().value());
		assertEquals(1.0, planet.colonists().quantity());
	}

	@Test
	void should_grow_population() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(10.0),
				new IndustryOf(1000.0),
				new PopulationOf(1000.0)
		);

		assertEquals(1000.0, planet.population().value());
		assertEquals(0.0, planet.colonists().quantity());

		planet.growPopulation();
		assertEquals(1000.0, planet.population().value());
		assertEquals(10.0, planet.colonists().quantity());
	}

}
