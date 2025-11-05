package galaxy.domain.ship;

public record Ship(Engines engines, Weapons weapons, Shields shields, Cargo cargo) {

	// TODO extract to Speed class/record
	public double speed() {
		// return BASE_SPEED * engines.value() * engines.techLevel() / weight();
		return new Speed(this).value();
	}

	public double weight() {
		return engines.weight() + weapons.weight() + shields.weight() + cargo.weight();
	}

	public double cargoWeight() {
		return cargo.loadedQuantity();
	}

}
