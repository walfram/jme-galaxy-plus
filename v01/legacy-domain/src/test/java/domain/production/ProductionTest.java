package domain.production;

import domain.planet.ClassicHomeWorld;
import domain.planet.Planet;
import domain.planet.properties.Colonists;
import domain.planet.properties.Coordinates;
import domain.planet.properties.Industry;
import domain.planet.properties.Materials;
import domain.technology.EnginesTechnology;
import domain.technology.Technology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductionTest {

	@Test
	void test_technology_production() {
		Planet planet = new ClassicHomeWorld(1L, new Coordinates(0.0, 0.0, 0.0));

		Technology technology = new EnginesTechnology();
		assertEquals(1.0, technology.value());

		Production production = new TechnologyProduction(planet, technology);
		production.execute();

		assertEquals(1.2, technology.value());
	}

	@Test
	void test_capital_production() {
		Planet planet = new ClassicHomeWorld(1L, new Coordinates(0.0, 0.0, 0.0));

		Industry before = planet.industry();
		assertEquals(1000.0, before.value());

		Production production = new CapitalProduction(planet);
		production.execute();

		Industry after = planet.industry();
		assertEquals(1196.078431372549, after.value());
	}

	@Test
	void test_materials_production() {
		Planet planet = new ClassicHomeWorld(1L, new Coordinates(0.0, 0.0, 0.0));

		Materials materials = planet.materials();

		assertEquals(0.0, materials.value());

		Production production = new MaterialsProduction(planet);
		production.execute();

		assertEquals(10000.0, materials.value());
	}

	@Test
	void test_population_production() {
		Planet planet = new ClassicHomeWorld(1L, new Coordinates(0.0, 0.0, 0.0));

		Colonists before = planet.colonists();
		assertEquals(0.0, before.value());

		Production production = new PopulationProduction(planet);
		production.execute();

		Colonists after = planet.colonists();
		assertEquals(10.0, after.value());
	}

}
