package domain.ship;

public record ShieldsTemplate(double value) {

	public ShieldsTemplate {
		if (value < 0) {
			throw new IllegalArgumentException("Shields value must be positive");
		}

		if (value > 0.0 && value < 1.0) {
			throw new IllegalArgumentException("Shields value must be between 0.0 and 1.0");
		}
	}

	public double weight() {
		return value;
	}
}
