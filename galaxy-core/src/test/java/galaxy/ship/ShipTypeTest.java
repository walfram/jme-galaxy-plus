package galaxy.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipTypeTest {

	@Test
	void test_ship_type_only_engines() {
		ShipType type = new ShipType("drone", new Engines(1.0));

		assertEquals(20.0, type.speed());
		assertEquals(1.0, type.mass());
	}

	@Test
	void test_ship_engines_and_shields() {
		ShipType flak = new ShipType("flak", new Engines(1.0), new Shields(2.0));
		assertEquals(3.0, flak.mass());
		assertEquals(6.666666666666667, flak.speed());

		ShipType fastFlak = new ShipType("fast-flak", new Engines(1.01), new Shields(1.01));
		assertEquals(2.02, fastFlak.mass());
		assertEquals(10.0, fastFlak.speed());

		ShipType drone = new ShipType("drone", new Engines(1.0), new Shields(1.0));
		assertEquals(2.0, drone.mass());
		assertEquals(10.0, drone.speed());
	}

	@Test
	void test_all_components() {
		ShipType type = new ShipType("drone-mk-2", new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new CargoBay(1.0));
		assertEquals(5.0, type.speed());
		assertEquals(4.0, type.mass());
	}

}
