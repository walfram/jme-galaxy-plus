package galaxy.domain.production;

import galaxy.domain.ClassicHomeWorld;
import galaxy.domain.Colonists;
import galaxy.domain.Coordinates;
import galaxy.domain.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionTest {

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
