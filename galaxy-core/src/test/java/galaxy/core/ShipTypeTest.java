package galaxy.core;

import galaxy.Fixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTypeTest {

	@Test
	void test_defence_turret() {
		ShipType type = Fixtures.testShipTypeTurret9x11();
//		assertEquals(89.63216799463753, type.effectiveDefence());
		assertEquals(22.92382813162085, type.effectiveDefence());
	}

	@Test
	void test_attack_battle_cruiser() {
		ShipType type = Fixtures.testShipTypeBattleCruiser();
		assertEquals(3.0, type.effectiveAttack());
	}

	@Test
	void test_attack_battle_station() {
		ShipType type = Fixtures.testShipTypeBattleStation();
		assertEquals(50.0, type.effectiveAttack());
	}

	@Test
	void test_attack_drone_armed() {
		ShipType type = Fixtures.testShipTypeDroneArmed();
		assertEquals(1.0, type.effectiveAttack());
	}

	@Test
	void test_attack_drone() {
		ShipType type = Fixtures.testShipTypeDrone();
		assertEquals(0.0, type.effectiveAttack());
	}

	@Test
	void test_speed_battle_station() {
		ShipType type = Fixtures.testShipTypeBattleStation();
		assertEquals(10.0, type.baseSpeed());
	}

	@Test
	void test_speed_battle_cruiser() {
		ShipType type = Fixtures.testShipTypeBattleCruiser();
		assertEquals(10.0, type.baseSpeed());
	}

	@Test
	void test_speed_drone() {
		ShipType type = Fixtures.testShipTypeDrone();
		assertEquals(20.0, type.baseSpeed());
	}

	@Test
	void test_battle_station() {
		ShipType type = Fixtures.testShipTypeBattleStation();
		assertEquals(198.0, type.mass());
	}

	@Test
	void test_mass_drone() {
		ShipType type = Fixtures.testShipTypeDrone();
		assertEquals(1.0, type.mass());
	}

	@Test
	void test_mass_battle_cruiser() {
		ShipType type = Fixtures.testShipTypeBattleCruiser();
		assertEquals(99.0, type.mass());
	}

}
