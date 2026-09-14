package galaxy.ship;

import galaxy.TechLevels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoTest {

	@Test
	void test_cargo_hold() {
		CargoHold cargoHold = new StandardCargoHold(1, new Cargo(10.0), new TechLevels());
		assertEquals(15.0, cargoHold.cargoCapacity().value());

		assertEquals(0.0, cargoHold.cargoMass());

		assertDoesNotThrow(() -> cargoHold.load(new CargoLoad(CargoType.COLONISTS, 15.0)));

		assertEquals(15.0, cargoHold.cargoMass());
	}

	@Test
	void test_cargo_capacity_tech_2() {
		TechLevels levels = new TechLevels(1, 1, 1, 2);

		assertEquals(0.0, new CargoCapacity(new Cargo(0.0), levels).value());

		assertEquals(2.1, new CargoCapacity(new Cargo(1.0), levels).value());
		assertEquals(12.5, new CargoCapacity(new Cargo(5.0), levels).value());
		assertEquals(30.0, new CargoCapacity(new Cargo(10.0), levels).value());

		assertEquals(350.0, new CargoCapacity(new Cargo(50.0), levels).value());
		assertEquals(1200.0, new CargoCapacity(new Cargo(100.0), levels).value());
	}

	@Test
	void test_cargo_capacity_tech_1() {
		TechLevels levels = new TechLevels(1, 1, 1, 1);

		assertEquals(0.0, new CargoCapacity(new Cargo(0.0), levels).value());

		assertEquals(1.05, new CargoCapacity(new Cargo(1.0), levels).value());
		assertEquals(6.25, new CargoCapacity(new Cargo(5.0), levels).value());
		assertEquals(15.0, new CargoCapacity(new Cargo(10.0), levels).value());

		assertEquals(117.85924500000002, new CargoCapacity(new Cargo(39.57), levels).value());

		assertEquals(175.0, new CargoCapacity(new Cargo(50.0), levels).value());
		assertEquals(600.0, new CargoCapacity(new Cargo(100.0), levels).value());
	}

}
