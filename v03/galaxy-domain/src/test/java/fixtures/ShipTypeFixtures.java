package fixtures;

import galaxy.ship.*;

public class ShipTypeFixtures {

	public static ShipType drone() {
		return new ShipType(new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new CargoHold(0.0), "drone");
	}

	public static ShipType flak() {
		return new ShipType(new Engines(1.00), new Weapons(0, 0.00), new Shields(2.00), new CargoHold(0.00), "Flak");
	}

	public static ShipType fastFlak() {
		return new ShipType(new Engines(1.01), new Weapons(0, 0.00), new Shields(1.01), new CargoHold(0.00), "FastFlak");
	}

	public static ShipType fighter() {
		return new ShipType(new Engines(2.48), new Weapons(1, 1.20), new Shields(1.27), new CargoHold(0.00), "Fighter");
	}

	public static ShipType gunship() {
		return new ShipType(new Engines(4.00), new Weapons(2, 2.00), new Shields(4.00), new CargoHold(0.00), "Gunship");
	}

	public static ShipType destroyer() {
		return new ShipType(new Engines(6.00), new Weapons(3, 4.00), new Shields(4.00), new CargoHold(0.00), "Destroyer");
	}

	public static ShipType cruiser() {
		return new ShipType(new Engines(16.50), new Weapons(30, 1.50), new Shields(9.75), new CargoHold(0.00), "Cruiser");
	}

	public static ShipType battleCruiser() {
		return new ShipType(new Engines(49.50), new Weapons(25, 3.00), new Shields(9.50), new CargoHold(1.00), "BattleCruiser");
	}

	public static ShipType battleship() {
		return new ShipType(new Engines(33.00), new Weapons(3, 25.00), new Shields(16.00), new CargoHold(1.00), "Battleship");
	}

	public static ShipType battleStation() {
		return new ShipType(new Engines(99.00), new Weapons(1, 50.00), new Shields(49.00), new CargoHold(0.00), "BattleStation");
	}

	public static ShipType orbitalFort() {
		return new ShipType(new Engines(0.00), new Weapons(11, 10.00), new Shields(39.00), new CargoHold(0.00), "OrbitalFort");
	}

	public static ShipType spaceGun() {
		return new ShipType(new Engines(0.00), new Weapons(1, 9.90), new Shields(9.90), new CargoHold(0.00), "SpaceGun");
	}

	public static ShipType hauler() {
		return new ShipType(new Engines(2.00), new Weapons(0, 0.00), new Shields(0.00), new CargoHold(1.00), "Hauler");
	}

	public static ShipType freighter() {
		return new ShipType(new Engines(30.00), new Weapons(0, 0.00), new Shields(9.50), new CargoHold(10.00), "Freighter");
	}

	public static ShipType megaFreighter() {
		return new ShipType(new Engines(120.00), new Weapons(0, 0.00), new Shields(38.43), new CargoHold(39.57), "MegaFreighter");
	}

}
