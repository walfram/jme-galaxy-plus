package galaxy.core.production;

import galaxy.core.Entity;
import galaxy.core.production.result.SpawnShips;
import galaxy.core.ship.ShipDesign;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ProductionTest {

	@Test
	void test_ships_production() {
		Entity planet = mock(Entity.class);
		Entity team = mock(Entity.class);

		ProductionContext context = new ProductionContext(planet, team);

		ShipDesign design = new ShipDesign(1, 1, 1, 1, 1);

		Production production = new ShipProduction(5, design);
		List<ProductionResult> results = production.complete(context);

		SpawnShips spawnShips = new SpawnShips(5, design);
		assertTrue(results.contains(spawnShips));
	}

}
