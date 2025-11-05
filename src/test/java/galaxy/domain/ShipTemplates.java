package galaxy.domain;

import galaxy.domain.ship.*;

public class ShipTemplates {

	public static ShipTemplate drone() {
		return new ShipTemplate(
				"drone",
				new EnginesTemplate(1.00),
				new WeaponsTemplate(0, 0.00),
				new ShieldsTemplate(0.00),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate flack() {
		return new ShipTemplate(
				"flack",
				new EnginesTemplate(1.00),
				new WeaponsTemplate(0, 0.00),
				new ShieldsTemplate(2.00),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate fastFlak() {
		return new ShipTemplate(
				"fastFlak",
				new EnginesTemplate(1.01),
				new WeaponsTemplate(0, 0.00),
				new ShieldsTemplate(1.01),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate fighter() {
		return new ShipTemplate(
				"fighter",
				new EnginesTemplate(2.48),
				new WeaponsTemplate(1, 1.20),
				new ShieldsTemplate(1.27),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate gunship() {
		return new ShipTemplate(
				"gunship",
				new EnginesTemplate(4.00),
				new WeaponsTemplate(2, 2.00),
				new ShieldsTemplate(4.00),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate destroyer() {
		return new ShipTemplate(
				"destroyer",
				new EnginesTemplate(6.00),
				new WeaponsTemplate(3, 4.00),
				new ShieldsTemplate(4.00),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate cruiser() {
		return new ShipTemplate(
				"cruiser",
				new EnginesTemplate(16.50),
				new WeaponsTemplate(30, 1.50),
				new ShieldsTemplate(9.75),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate battleCruiser() {
		return new ShipTemplate(
				"battleCruiser",
				new EnginesTemplate(49.50),
				new WeaponsTemplate(25, 3.00),
				new ShieldsTemplate(9.50),
				new CargoTemplate(1.00)
		);
	}

	public static ShipTemplate battleship() {
		return new ShipTemplate(
				"battleship",
				new EnginesTemplate(33.00),
				new WeaponsTemplate(3, 25.00),
				new ShieldsTemplate(16.00),
				new CargoTemplate(1.00)
		);
	}

	public static ShipTemplate battleStation() {
		return new ShipTemplate(
				"battleStation",
				new EnginesTemplate(99.00),
				new WeaponsTemplate(1, 50.00),
				new ShieldsTemplate(49.00),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate orbitalFort() {
		return new ShipTemplate(
				"orbitalFort",
				new EnginesTemplate(0.00),
				new WeaponsTemplate(11, 10.00),
				new ShieldsTemplate(39.00),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate spaceGun() {
		return new ShipTemplate(
				"spaceGun",
				new EnginesTemplate(0.00),
				new WeaponsTemplate(1, 9.90),
				new ShieldsTemplate(9.90),
				new CargoTemplate(0.00)
		);
	}

	public static ShipTemplate hauler() {
		return new ShipTemplate(
				"hauler",
				new EnginesTemplate(2.00),
				new WeaponsTemplate(0, 0.00),
				new ShieldsTemplate(0.00),
				new CargoTemplate(1.00)
		);
	}

	public static ShipTemplate freighter() {
		return new ShipTemplate(
				"freighter",
				new EnginesTemplate(30.00),
				new WeaponsTemplate(0, 0.00),
				new ShieldsTemplate(9.50),
				new CargoTemplate(10.00)
		);
	}

	public static ShipTemplate megaFreighter() {
		return new ShipTemplate(
				"megaFreighter",
				new EnginesTemplate(120.00),
				new WeaponsTemplate(0, 0.00),
				new ShieldsTemplate(38.43),
				new CargoTemplate(39.57)
		);
	}

}
