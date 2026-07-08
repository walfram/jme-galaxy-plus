package domain.ship;

public record CargoTemplate(double value) {

	public CargoTemplate {
		if (value < 0) {
			throw new IllegalArgumentException("Cargo value must be positive");
		}

		if (value > 0.0 && value < 1.0) {
			throw new IllegalArgumentException("Cargo value must be between 0.0 and 1.0");
		}
	}

	public double weight() {
		return value;
	}

	public double capacity() {
		return new CargoCapacity(this).value();
	}
}
