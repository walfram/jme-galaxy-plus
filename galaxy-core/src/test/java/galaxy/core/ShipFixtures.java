package galaxy.core;

import galaxy.core.ship.ShipDesign;

public class ShipFixtures {

	public static ShipDesign drone() {
		return new ShipDesign(1.00, 0, 0.00, 0.00, 0.00);
	}

	public static ShipDesign flack() {
		return new ShipDesign(1.00, 0, 0.00, 2.00, 0.00);
	}

	public static ShipDesign fastFlak() {
		return new ShipDesign(1.01, 0, 0.00, 1.01, 0.00);
	}

	public static ShipDesign fighter() {
		return new ShipDesign(2.48, 1, 1.20, 1.27, 0.00);
	}

	public static ShipDesign gunship() {
		return new ShipDesign(4.00, 2, 2.00, 4.00, 0.00);
	}

	public static ShipDesign destroyer() {
		return new ShipDesign(6.00, 3, 4.00, 4.00, 0.00);
	}

	public static ShipDesign cruiser() {
		return new ShipDesign(16.50, 30, 1.50, 9.75, 0.00);
	}

	public static ShipDesign battleCruiser() {
		return new ShipDesign(49.50, 25, 3.00, 9.50, 1.00);
	}

	public static ShipDesign battleShip() {
		return new ShipDesign(33.00, 3, 25.00, 16.00, 1.00);
	}

	public static ShipDesign battleStation() {
		return new ShipDesign(99.00, 1, 50.00, 49.00, 0.00);
	}

	public static ShipDesign orbitalFort() {
		return new ShipDesign(0.00, 11, 10.00, 39.00, 0.00);
	}

	public static ShipDesign spaceGun() {
		return new ShipDesign(0.00, 1, 9.90, 9.90, 0.00);
	}

	public static ShipDesign hauler() {
		return new ShipDesign(2.00, 0, 0.00, 0.00, 1.00);
	}

	public static ShipDesign freighter() {
		return new ShipDesign(30.00, 0, 0.00, 9.50, 10.00);
	}

	public static ShipDesign megaFreighter() {
		return new ShipDesign(120.00, 0, 0.00, 38.43, 39.57);
	}

}
