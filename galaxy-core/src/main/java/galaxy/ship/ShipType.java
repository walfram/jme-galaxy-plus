package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.TechLevels;

public record ShipType(String name, Engines engines, Weapons weapons, Shields shields, Cargo cargo) {

	public ShipType(String name, JsonNode src) {
		this(
				name,
				new Engines(src.get("engines")),
				new Weapons(src.get("weapons")),
				new Shields(src.get("shields")),
				new Cargo(src.get("cargo"))
		);
	}

	public double mass() {
		return engines.size()
				+ weaponsMass()
				+ shields.size()
				+ cargo.size();
	}

	private double weaponsMass() {
		return weapons.caliber() * (weapons.guns() + 1) / 2.0;
	}

	public CargoHold cargoHold(int size, TechLevels techLevels, CargoLoad cargoLoad) {
		if (cargo.size() == 0) {
			return new NoCargoHold();
		} else {
			StandardCargoHold cargoHold = new StandardCargoHold(size, cargo, techLevels);
			cargoHold.load(cargoLoad);
			return cargoHold;
		}
	}
}
