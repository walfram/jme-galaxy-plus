package galaxy.ship;

import galaxy.Scalar;

public record ShieldsPowerOf(Double value) implements ShieldsPower {

	public ShieldsPowerOf {
		if (value == null) {
			throw new IllegalArgumentException("Shields power cannot be null");
		}
	}

	public ShieldsPowerOf(Scalar<Double> source) {
		this(source.value());
	}

	@Override
	public Double mass() {
		return value();
	}
}
