package galaxy.domain.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTemplateTest {

	@Test
	void test_weapons_template() {
		assertDoesNotThrow(() -> new WeaponsTemplate(1, 1.0));
		assertDoesNotThrow(() -> new WeaponsTemplate(0, 0.0));

		assertThrows(IllegalArgumentException.class, () -> new WeaponsTemplate(0, 1.0));
		assertThrows(IllegalArgumentException.class, () -> new WeaponsTemplate(1, 0.0));
		assertThrows(IllegalArgumentException.class, () -> new WeaponsTemplate(1, 0.5));
	}

	@Test
	void test_cargo_template() {
		assertDoesNotThrow(() -> new CargoTemplate(1.0));
		assertDoesNotThrow(() -> new CargoTemplate(0.0));

		assertThrows(IllegalArgumentException.class, () -> new CargoTemplate(0.5));
		assertThrows(IllegalArgumentException.class, () -> new CargoTemplate(-1.0));
	}

	@Test
	void test_shields_template() {
		assertDoesNotThrow(() -> new ShieldsTemplate(1.0));
		assertDoesNotThrow(() -> new ShieldsTemplate(0.0));

		assertThrows(IllegalArgumentException.class, () -> new ShieldsTemplate(0.5));
		assertThrows(IllegalArgumentException.class, () -> new ShieldsTemplate(-1.0));
	}

	@Test
	void test_engines_template() {
		assertDoesNotThrow(() -> new EnginesTemplate(1.0));
		assertDoesNotThrow(() -> new EnginesTemplate(0.0));

		assertThrows(IllegalArgumentException.class, () -> new EnginesTemplate(0.5));
		assertThrows(IllegalArgumentException.class, () -> new EnginesTemplate(-1.0));
	}

	@Test
	void test_gunship_template() {
		ShipTemplate gunship = assertDoesNotThrow(() -> new ShipTemplate(
				"gunship",
				new EnginesTemplate(4.0),
				new WeaponsTemplate(2, 2.0),
				new ShieldsTemplate(4.0), new CargoTemplate(0.0)
		));

		assertEquals(11.0, gunship.weight());
	}

	@Test
	void test_cruiser_template() {
		ShipTemplate cruiser = assertDoesNotThrow(() -> new ShipTemplate(
				"cruiser",
				new EnginesTemplate(15.0),
				new WeaponsTemplate(1, 15.0),
				new ShieldsTemplate(15.0), new CargoTemplate(0.0)
		));

		assertEquals(45.0, cruiser.weight());
	}

	@Test
	void test_freighter_template() {
		ShipTemplate freighter = assertDoesNotThrow(() -> new ShipTemplate(
				"freighter",
				new EnginesTemplate(8.0),
				new WeaponsTemplate(0, 0.0),
				new ShieldsTemplate(2.0), new CargoTemplate(10.0)
		));

		assertEquals(20.0, freighter.weight());
	}

	@Test
	void test_drone_template() {
		ShipTemplate drone = assertDoesNotThrow(() -> new ShipTemplate(
				"drone",
				new EnginesTemplate(1.0),
				new WeaponsTemplate(0, 0.0),
				new ShieldsTemplate(0.0), new CargoTemplate(0.0)
		));

		assertEquals(1.0, drone.weight());
	}

}
