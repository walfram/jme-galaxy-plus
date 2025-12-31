package galaxy.core;

import galaxy.core.derived.Speed;
import galaxy.core.planet.Effort;
import galaxy.core.planet.Industry;
import galaxy.core.planet.Population;
import galaxy.core.ship.ShipDesign;
import galaxy.core.ship.TechLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyMathTest {

	@Test
	void test_speed_mega_freighter() {
		ShipDesign design = ShipFixtures.megaFreighter();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(12.121212121212121, speed.value());
		assertEquals(7.59832120791652, speed.valueLoaded());
	}

	@Test
	void test_speed_freighter() {
		ShipDesign design = ShipFixtures.freighter();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(12.121212121212121, speed.value());
		assertEquals(9.30232558139535, speed.valueLoaded());
	}

	@Test
	void test_speed_hauler() {
		ShipDesign design = ShipFixtures.hauler();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(13.333333333333332, speed.value());
		assertEquals(9.876543209876544, speed.valueLoaded());
	}

	@Test
	void test_speed_space_gun() {
		ShipDesign design = ShipFixtures.spaceGun();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(0.0, speed.value());
		assertEquals(0.0, speed.valueLoaded());
	}

	@Test
	void test_speed_orbital_fort() {
		ShipDesign design = ShipFixtures.orbitalFort();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(0.0, speed.value());
		assertEquals(0.0, speed.valueLoaded());
	}

	@Test
	void test_speed_battle_station() {
		ShipDesign design = ShipFixtures.battleStation();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(10.00, speed.value());
		assertEquals(10.00, speed.valueLoaded());
	}

	@Test
	void test_speed_battle_ship() {
		ShipDesign design = ShipFixtures.battleShip();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(6.6000000000000005, speed.value());
		assertEquals(6.53142008906482, speed.valueLoaded());
	}

	@Test
	void test_speed_battle_cruiser() {
		ShipDesign design = ShipFixtures.battleCruiser();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(10.00, speed.value());
		assertEquals(9.89505247376312, speed.valueLoaded());
	}

	@Test
	void test_speed_cruiser() {
		ShipDesign design = ShipFixtures.cruiser();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(6.666666666666666, speed.value());
		assertEquals(6.666666666666666, speed.valueLoaded());
	}

	@Test
	void test_speed_destroyer() {
		ShipDesign design = ShipFixtures.destroyer();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(6.666666666666666, speed.value());
		assertEquals(6.666666666666666, speed.valueLoaded());
	}

	@Test
	void test_speed_gunship() {
		ShipDesign design = ShipFixtures.gunship();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(7.272727272727273, speed.value());
		assertEquals(7.272727272727273, speed.valueLoaded());
	}

	@Test
	void test_speed_fighter() {
		ShipDesign design = ShipFixtures.fighter();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(10.020202020202023, speed.value());
		assertEquals(10.020202020202023, speed.valueLoaded());
	}

	@Test
	void test_speed_fast_flack() {
		ShipDesign design = ShipFixtures.fastFlak();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(10.00, speed.value());
		assertEquals(10.00, speed.valueLoaded());
	}

	@Test
	void test_speed_flack() {
		ShipDesign design = ShipFixtures.flack();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(6.666666666666666, speed.value());
		assertEquals(6.666666666666666, speed.valueLoaded());
	}

	@Test
	void test_speed_drone() {
		ShipDesign design = ShipFixtures.drone();
		Speed speed = new Speed(design, new TechLevel());
		assertEquals(20.0, speed.value());
		assertEquals(20.0, speed.valueLoaded());
	}

	@Test
	void test_speed_colonizer() {
		ShipDesign design = new ShipDesign(10, 0, 0, 0, 1);
		Speed speed = new Speed(design, new TechLevel(1, 1, 1, 1));
		assertEquals(10.0, speed.value());
		assertEquals(10.0, speed.valueLoaded());
	}

	@Test
	void test_effort() {
		Effort effort_1000_500 = new Effort(new Population(1000.0), new Industry(500.0));
		assertEquals(625.0, effort_1000_500.value());

		Effort effort_500_200 = new Effort(new Population(500.0), new Industry(200.0));
		assertEquals(275.0, effort_500_200.value());
	}

	@Test
		// no materials, production output = 99.0099
	void test_production_math() {
		double effort = 1000.0;
		double resources = 10.0;

		double weight = ShipFixtures.battleShipAlt().weight();

		double resourceRatio = 1.0 / resources;

		double shipCost = weight * 10.0;
		double materialCost = weight * resourceRatio;

		double costPerUnit = shipCost + materialCost;

		double shipsPerTurn = effort / costPerUnit;
		assertEquals(1.1001100110011002, shipsPerTurn);

		int ships = (int) Math.floor(shipsPerTurn);
		assertEquals(1, ships);

		double remainder = costPerUnit * (shipsPerTurn - ships);
		assertEquals(9.10891089108911, remainder);
	}

}
