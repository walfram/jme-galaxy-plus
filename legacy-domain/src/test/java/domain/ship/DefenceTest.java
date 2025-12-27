package domain.ship;

import domain.Fixtures;
import domain.technology.Technologies;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefenceTest {

	@Test
	void test_compare_defences() {
		ShipTemplate largeTemplate = new ShipTemplate(
				"large",
				new EnginesTemplate(8),
				new WeaponsTemplate(1, 8),
				new ShieldsTemplate(8),
				new CargoTemplate(0)
		);
		Ship large = largeTemplate.build(new Technologies(), null, null);

		ShipTemplate smallTemplate = new ShipTemplate(
				"small",
				new EnginesTemplate(1),
				new WeaponsTemplate(1, 1),
				new ShieldsTemplate(1),
				new CargoTemplate(0)
		);
		Ship small = smallTemplate.build(new Technologies(), null, null);

		double largeDefence = new Defence(large).value();
		double smallDefence = new Defence(small).value();

		assertEquals(4.0, largeDefence / smallDefence);
	}

	@Test
	void test_ship_shields() {
		ShipTemplate template = new ShipTemplate(
				"test",
				new EnginesTemplate(10),
				new WeaponsTemplate(1, 10.0),
				new ShieldsTemplate(10.0),
				new CargoTemplate(0.0)
		);

		Ship ship = template.build(new Technologies(), null, null);
		double shields = new Defence(ship).value();

		assertEquals(10.0, shields);
	}

	@Test
	void test_ship_defence() {
		Ship sample = Fixtures.sampleShip();
		double defence = new Defence(sample).value();

		assertEquals(89.63216799463753, defence);
	}

}
