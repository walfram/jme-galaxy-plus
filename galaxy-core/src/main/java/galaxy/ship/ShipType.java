package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record ShipType(String name, Engines engines, Weapons weapons, Shields shields, CargoBay cargoBay) {

	public ShipType(String name, Engines engines) {
		this(name, engines, new NoWeapons(), new NoShields(), new NoCargoBay());
	}

	public ShipType(String name, Engines engines, Shields shields) {
		this(name, engines, new NoWeapons(), shields, new NoCargoBay());
	}

	public ShipType(String name, Engines engines, CargoBay cargoBay) {
		this(name, engines, new NoWeapons(), new NoShields(), cargoBay);
	}

	public ShipType(String name, Engines engines, Shields shields, CargoBay cargoBay) {
		this(name, engines, new NoWeapons(), shields, cargoBay);
	}

	public ShipType(String name, JsonNode src) {
		this(
				name,
				new EnginesOf(src.required("engines")),
				new WeaponsOf(src.required("weapons")),
				new ShieldsOf(src.required("shields")),
				new CargoBayOf(src.required("cargo"))
		);
	}

	public ShipType(String name, CargoBay cargoBay) {
		this(name, new NoEngines(), new NoWeapons(), new NoShields(), cargoBay);
	}

	public double speed() {
		return 20.0 * engines.size() / mass();
	}

	public double mass() {
		return engines.mass() + weapons.mass() + shields.mass() + cargoBay.mass();
	}


}
