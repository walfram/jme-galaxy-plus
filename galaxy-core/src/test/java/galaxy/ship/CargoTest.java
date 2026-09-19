package galaxy.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoTest {

	@Test
	void test_cargo_hold() {
		CargoBay cargoHold = new CargoBayOf(10.0);
		assertEquals(15.0, cargoHold.capacity());

		// TODO assertEquals(0.0, cargoHold.cargoMass());
		// TODO assertDoesNotThrow(() -> cargoHold.load(new ColonistsOf(15.0)));
		// TODO assertEquals(15.0, cargoHold.cargoMass());
	}

	@Test
	void test_cargo_capacity_tech_2() {
		final TechLevel levels = new TechLevel(2.0);

		assertEquals(0.0, new NoCargoBay().capacity());

		assertEquals(2.1, new CargoBayOf(1.0, levels).capacity());
		assertEquals(12.5, new CargoBayOf(5.0, levels).capacity());
		assertEquals(30.0, new CargoBayOf(10.0, levels).capacity());

		assertEquals(350.0, new CargoBayOf(50.0, levels).capacity());
		assertEquals(1200.0, new CargoBayOf(100.0, levels).capacity());
	}

	@Test
	void test_cargo_capacity_tech_1() {
		final TechLevel levels = new TechLevel();

		assertEquals(0.0, new NoCargoBay().capacity());

		assertEquals(1.05, new CargoBayOf(1.0, levels).capacity());
		assertEquals(6.25, new CargoBayOf(5.0, levels).capacity());
		assertEquals(15.0, new CargoBayOf(10.0, levels).capacity());

		assertEquals(117.85924500000002, new CargoBayOf(39.57, levels).capacity());

		assertEquals(175.0, new CargoBayOf(50.0, levels).capacity());
		assertEquals(600.0, new CargoBayOf(100.0, levels).capacity());
	}

}
