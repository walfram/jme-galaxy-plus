package galaxy.ship;

public record ShipType(Engines engines, Weapons weapons, Shields shields, CargoHold cargoHold, String name) {

	public double mass() {
		double engineWeight = engines.mass();
		double weaponsWeight = (weapons.guns() + 1) * (weapons.caliber() / 2.0);
		double shieldsWeight = shields.mass();
		double cargoWeight = cargoHold.mass();

		return engineWeight + weaponsWeight + shieldsWeight + cargoWeight;
	}

	public double speed() {
		double driveTech = 1.0;
		double effectiveCargoCarried = 0.0;

		return 20 * driveTech * (engines.mass() / (mass() + effectiveCargoCarried));
	}

}
