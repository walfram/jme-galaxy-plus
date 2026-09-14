package galaxy;

import galaxy.ship.*;

public class Fixtures {

	public static ShipType drone() {
		return new ShipType("drone", new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new Cargo(0.0));
	}

	public static ShipType flak() {
		return new ShipType("Flak", new Engines(1.00), new Weapons(0, 0.00), new Shields(2.00), new Cargo(0.00));
	}

	public static ShipType fastFlak() {
		return new ShipType("FastFlak", new Engines(1.01), new Weapons(0, 0.00), new Shields(1.01), new Cargo(0.00));
	}

	public static ShipType fighter() {
		return new ShipType("Fighter", new Engines(2.48), new Weapons(1, 1.20), new Shields(1.27), new Cargo(0.00));
	}

	public static ShipType gunship() {
		return new ShipType("Gunship", new Engines(4.00), new Weapons(2, 2.00), new Shields(4.00), new Cargo(0.00));
	}

	public static ShipType destroyer() {
		return new ShipType("Destroyer", new Engines(6.00), new Weapons(3, 4.00), new Shields(4.00), new Cargo(0.00));
	}

	public static ShipType cruiser() {
		return new ShipType("Cruiser", new Engines(16.50), new Weapons(30, 1.50), new Shields(9.75), new Cargo(0.00));
	}

	public static ShipType battleCruiser() {
		return new ShipType("BattleCruiser", new Engines(49.50), new Weapons(25, 3.00), new Shields(9.50), new Cargo(1.00));
	}

	public static ShipType battleship() {
		return new ShipType("Battleship", new Engines(33.00), new Weapons(3, 25.00), new Shields(16.00), new Cargo(1.00));
	}

	public static ShipType battleStation() {
		return new ShipType("BattleStation", new Engines(99.00), new Weapons(1, 50.00), new Shields(49.00), new Cargo(0.00));
	}

	public static ShipType orbitalFort() {
		return new ShipType("OrbitalFort", new Engines(0.00), new Weapons(11, 10.00), new Shields(39.00), new Cargo(0.00));
	}

	public static ShipType spaceGun() {
		return new ShipType("SpaceGun", new Engines(0.00), new Weapons(1, 9.90), new Shields(9.90), new Cargo(0.00));
	}

	public static ShipType hauler() {
		return new ShipType("Hauler", new Engines(2.00), new Weapons(0, 0.00), new Shields(0.00), new Cargo(1.00));
	}

	public static ShipType freighter() {
		return new ShipType("Freighter", new Engines(30.00), new Weapons(0, 0.00), new Shields(9.50), new Cargo(10.00));
	}

	public static ShipType megaFreighter() {
		return new ShipType("MegaFreighter", new Engines(120.00), new Weapons(0, 0.00), new Shields(38.43), new Cargo(39.57));
	}
	
}
