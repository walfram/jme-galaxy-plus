package galaxy.fixtures;

import galaxy.ShipType;
import galaxy.decorators.ShipTypeOf;
import galaxy.ship.CargoSizeOf;
import galaxy.ship.EngineSizeOf;
import galaxy.ship.WeaponsOf;
import galaxy.ship.ShieldsPowerOf;

public class ShipTypeFixtures {

	public static ShipType drone() {
		return new ShipTypeOf("drone", new EngineSizeOf(1.0), new WeaponsOf(0, 0.0), new ShieldsPowerOf(0.0), new CargoSizeOf(0.0));
	}

	public static ShipType flak() {
		return new ShipTypeOf("Flak", new EngineSizeOf(1.00), new WeaponsOf(0, 0.00), new ShieldsPowerOf(2.00), new CargoSizeOf(0.00));
	}

	public static ShipType fastFlak() {
		return new ShipTypeOf("FastFlak", new EngineSizeOf(1.01), new WeaponsOf(0, 0.00), new ShieldsPowerOf(1.01), new CargoSizeOf(0.00));
	}

	public static ShipType fighter() {
		return new ShipTypeOf("Fighter", new EngineSizeOf(2.48), new WeaponsOf(1, 1.20), new ShieldsPowerOf(1.27), new CargoSizeOf(0.00));
	}

	public static ShipType gunship() {
		return new ShipTypeOf("Gunship", new EngineSizeOf(4.00), new WeaponsOf(2, 2.00), new ShieldsPowerOf(4.00), new CargoSizeOf(0.00));
	}

	public static ShipType destroyer() {
		return new ShipTypeOf("Destroyer", new EngineSizeOf(6.00), new WeaponsOf(3, 4.00), new ShieldsPowerOf(4.00), new CargoSizeOf(0.00));
	}

	public static ShipType cruiser() {
		return new ShipTypeOf("Cruiser", new EngineSizeOf(16.50), new WeaponsOf(30, 1.50), new ShieldsPowerOf(9.75), new CargoSizeOf(0.00));
	}

	public static ShipType battleCruiser() {
		return new ShipTypeOf("BattleCruiser", new EngineSizeOf(49.50), new WeaponsOf(25, 3.00), new ShieldsPowerOf(9.50), new CargoSizeOf(1.00));
	}

	public static ShipType battleship() {
		return new ShipTypeOf("Battleship", new EngineSizeOf(33.00), new WeaponsOf(3, 25.00), new ShieldsPowerOf(16.00), new CargoSizeOf(1.00));
	}

	public static ShipType battleStation() {
		return new ShipTypeOf("BattleStation", new EngineSizeOf(99.00), new WeaponsOf(1, 50.00), new ShieldsPowerOf(49.00), new CargoSizeOf(0.00));
	}

	public static ShipType orbitalFort() {
		return new ShipTypeOf("OrbitalFort", new EngineSizeOf(0.00), new WeaponsOf(11, 10.00), new ShieldsPowerOf(39.00), new CargoSizeOf(0.00));
	}

	public static ShipType spaceGun() {
		return new ShipTypeOf("SpaceGun", new EngineSizeOf(0.00), new WeaponsOf(1, 9.90), new ShieldsPowerOf(9.90), new CargoSizeOf(0.00));
	}

	public static ShipType hauler() {
		return new ShipTypeOf("Hauler", new EngineSizeOf(2.00), new WeaponsOf(0, 0.00), new ShieldsPowerOf(0.00), new CargoSizeOf(1.00));
	}

	public static ShipType freighter() {
		return new ShipTypeOf("Freighter", new EngineSizeOf(30.00), new WeaponsOf(0, 0.00), new ShieldsPowerOf(9.50), new CargoSizeOf(10.00));
	}

	public static ShipType megaFreighter() {
		return new ShipTypeOf("MegaFreighter", new EngineSizeOf(120.00), new WeaponsOf(0, 0.00), new ShieldsPowerOf(38.43), new CargoSizeOf(39.57));
	}
	
}
