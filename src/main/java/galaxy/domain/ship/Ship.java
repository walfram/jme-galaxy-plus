package galaxy.domain.ship;

public record Ship(Engines engines, Weapons weapons, Shields shields, Cargo cargo) {

	private static final double BASE_SPEED = 20.0;

	// TODO extract to Speed class/record
	public double speed() {
		return BASE_SPEED * engines.value() * engines.techLevel() / weight();
	}

	public double weight() {
		double weight = engines.weight() + weapons.weight() + shields.weight() + cargo.weight();

		double cargoTechLevel = cargo.techLevel();

		if (cargoTechLevel > 0) {
			weight += cargo.loadedQuantity() / cargoTechLevel;
		}

		return weight;
	}


}
