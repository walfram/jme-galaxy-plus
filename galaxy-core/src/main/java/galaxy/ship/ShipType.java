package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record ShipType(String name, Engines engines, Weapons weapons, Shields shields, CargoBay cargoBay) {

	public ShipType(String name, Engines engines) {
		this(name, engines, new Weapons(), new Shields(), new CargoBay());
	}

	public ShipType(String name, Engines engines, Shields shields) {
		this(name, engines, new Weapons(), shields, new CargoBay());
	}

	public ShipType(String name, Engines engines, CargoBay cargoBay) {
		this(name, engines, new Weapons(), new Shields(), cargoBay);
	}

	public ShipType(String name, Engines engines, Shields shields, CargoBay cargoBay) {
		this(name, engines, new Weapons(), shields, cargoBay);
	}

	public ShipType(String name, JsonNode src) {
		this(
				name,
				new Engines(src.required("engines")),
				new Weapons(src.required("weapons")),
				new Shields(src.required("shields")),
				new CargoBay(src.required("cargo"))
		);
	}

	public ShipType(String name, CargoBay cargoBay) {
		this(name, new Engines(), new Weapons(), new Shields(), cargoBay);
	}

	public double speed() {
		return 20.0 * engines.size() / mass();
	}

	public double mass() {
		return engines.mass() + weapons.mass() + shields.mass() + cargoBay.mass();
	}

}
