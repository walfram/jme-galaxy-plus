package galaxy.domain.production;

import galaxy.domain.Entity;
import galaxy.domain.production.result.SpawnShips;
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

		Production production = new ShipProduction();
		List<ProductionResult> results = production.complete(context);

		Object blueprint = "";
		assertTrue(results.contains(new SpawnShips(3, blueprint)));
	}

}
