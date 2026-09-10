package galaxy;

import galaxy.decorators.ShipGroupOf;
import galaxy.decorators.ShipTypeOf;
import galaxy.ship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ShipGroupTest {

	@Test
	void test_ship_group_weight() {
		Race race = mock(Race.class);
		ShipType shipType = new ShipTypeOf("drone-mk-2", new EngineSizeOf(1.0), new WeaponsOf(1, 1.0), new ShieldsPowerOf(1.0), new CargoSizeOf(1.0));
		int size = 10;
		Planet planet = mock(Planet.class);

		ShipGroup shipGroup = new ShipGroupOf(race, shipType, size, planet);
		Scalar<Double> mass = new MassOf(shipGroup);

		assertEquals(4.0, mass.value());
	}

}
