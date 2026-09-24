package galaxy.phase;

import galaxy.Fixtures;
import galaxy.Production;
import galaxy.production.ShipGroupBuildProduction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionPhaseTest {

	@Test
	void test_homeworld_should_produce_99_drones() {
		Production p = new ShipGroupBuildProduction(Fixtures.drone());
	}

}
