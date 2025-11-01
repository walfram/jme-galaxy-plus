package galaxy.domain.production;

import galaxy.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionTest {

	@Test
	void test_materials_production() {
		Planet planet = new ClassicHomeWorld("test", new Coordinates(0.0, 0.0, 0.0));

		Materials materials = planet.materials();

		assertEquals(0.0, materials.value());

		Production production = new MaterialsProduction(planet);
		production.execute();

		assertEquals(10000.0, materials.value());
	}

	@Test
	void test_population_production() {
		Planet planet = new ClassicHomeWorld("test", new Coordinates(0.0, 0.0, 0.0));

		Colonists before = planet.colonists();
		assertEquals(0.0, before.value());

		Production production = new PopulationProduction(planet);
		production.execute();

		Colonists after = planet.colonists();
		assertEquals(10.0, after.value());
	}

}
