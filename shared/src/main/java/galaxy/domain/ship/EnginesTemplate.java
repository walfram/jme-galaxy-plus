package galaxy.domain.ship;

public record EnginesTemplate(double value) {

	public EnginesTemplate {
		if (value < 0) {
			throw new IllegalArgumentException("Engines value must be positive");
		}

		if (value > 0.0 && value < 1.0) {
			throw new IllegalArgumentException("Engines value must be between 0.0 and 1.0");
		}
	}

	public EnginesTemplate(EnginesTemplate source) {
		this(source.value);
	}

	public double weight() {
		return value;
	}

}
