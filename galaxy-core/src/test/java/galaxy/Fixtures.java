package galaxy;

import galaxy.ship.*;

public class Fixtures {

	public static ShipType drone() {
		return new ShipType("drone", new EnginesOf(1.0), new WeaponsOf(0, 0.0), new ShieldsOf(0.0), new CargoBayOf(0.0));
	}

	public static ShipType droneMk2() {
		return new ShipType("drone", new EnginesOf(1.0), new WeaponsOf(1, 1.0), new ShieldsOf(1.0), new CargoBayOf(1.0));
	}

	public static ShipType flak() {
		return new ShipType("Flak", new EnginesOf(1.00), new WeaponsOf(0, 0.00), new ShieldsOf(2.00), new CargoBayOf(0.00));
	}

	public static ShipType fastFlak() {
		return new ShipType("FastFlak", new EnginesOf(1.01), new WeaponsOf(0, 0.00), new ShieldsOf(1.01), new CargoBayOf(0.00));
	}

	public static ShipType fighter() {
		return new ShipType("Fighter", new EnginesOf(2.48), new WeaponsOf(1, 1.20), new ShieldsOf(1.27), new CargoBayOf(0.00));
	}

	public static ShipType gunship() {
		return new ShipType("Gunship", new EnginesOf(4.00), new WeaponsOf(2, 2.00), new ShieldsOf(4.00), new CargoBayOf(0.00));
	}

	public static ShipType destroyer() {
		return new ShipType("Destroyer", new EnginesOf(6.00), new WeaponsOf(3, 4.00), new ShieldsOf(4.00), new CargoBayOf(0.00));
	}

	public static ShipType cruiser() {
		return new ShipType("Cruiser", new EnginesOf(16.50), new WeaponsOf(30, 1.50), new ShieldsOf(9.75), new CargoBayOf(0.00));
	}

	public static ShipType battleCruiser() {
		return new ShipType("BattleCruiser", new EnginesOf(49.50), new WeaponsOf(25, 3.00), new ShieldsOf(9.50), new CargoBayOf(1.00));
	}

	public static ShipType battleship() {
		return new ShipType("Battleship", new EnginesOf(33.00), new WeaponsOf(3, 25.00), new ShieldsOf(16.00), new CargoBayOf(1.00));
	}

	public static ShipType battleStation() {
		return new ShipType("BattleStation", new EnginesOf(99.00), new WeaponsOf(1, 50.00), new ShieldsOf(49.00), new CargoBayOf(0.00));
	}

	public static ShipType orbitalFort() {
		return new ShipType("OrbitalFort", new EnginesOf(0.00), new WeaponsOf(11, 10.00), new ShieldsOf(39.00), new CargoBayOf(0.00));
	}

	public static ShipType spaceGun() {
		return new ShipType("SpaceGun", new EnginesOf(0.00), new WeaponsOf(1, 9.90), new ShieldsOf(9.90), new CargoBayOf(0.00));
	}

	public static ShipType hauler() {
		return new ShipType("Hauler", new EnginesOf(2.00), new WeaponsOf(0, 0.00), new ShieldsOf(0.00), new CargoBayOf(1.00));
	}

	public static ShipType freighter() {
		return new ShipType("Freighter", new EnginesOf(30.00), new WeaponsOf(0, 0.00), new ShieldsOf(9.50), new CargoBayOf(10.00));
	}

	public static ShipType megaFreighter() {
		return new ShipType("MegaFreighter", new EnginesOf(120.00), new WeaponsOf(0, 0.00), new ShieldsOf(38.43), new CargoBayOf(39.57));
	}
}
