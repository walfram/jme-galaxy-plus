package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record ShipType(Engines engines, Weapons weapons, Shields shields, CargoHold cargoHold, String name) {

	public ShipType(JsonNode src) {
		this(
				new Engines(src.path("engines").asDouble()),
				new Weapons(src.path("weapons")),
				new Shields(src.path("shields").asDouble()),
				new CargoHold(src.path("cargoHold").asDouble()),
				src.path("name").asText()
		);
	}

	public double mass() {
		double engineWeight = engines.mass();
		double weaponsWeight = (weapons.guns() + 1) * (weapons.caliber() / 2.0);
		double shieldsWeight = shields.mass();
		double cargoWeight = cargoHold.mass();

		return engineWeight + weaponsWeight + shieldsWeight + cargoWeight;
	}

	public double speed() {
		double engineTechLevel = 1.0;
		double effectiveCargoWeight = 0.0;
		return speed(engineTechLevel, effectiveCargoWeight);
	}

	public double speed(double engineTech, double effectiveCargoWeight) {
		return 20.0 * engineTech * (engines.mass() / (mass() + effectiveCargoWeight));
	}

	public double effectiveCargoWeight() {
		return cargoHold.mass() + ((cargoHold.mass() * cargoHold.mass()) / 10.0);
	}
}
