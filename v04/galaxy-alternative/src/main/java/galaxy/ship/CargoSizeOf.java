package galaxy.ship;

import galaxy.Scalar;

public record CargoSizeOf(Double value) implements CargoSize {

	public CargoSizeOf {
		if (value == null) {
			throw new IllegalArgumentException("Cargo size cannot be null");
		}
	}

	public CargoSizeOf(Scalar<Double> source) {
		this(source.value());
	}

	@Override
	public boolean isPresent() {
		return mass() > 0.0;
	}

	@Override
	public Double mass() {
		return value();
	}

	@Override
	public Cargo toCargo() {
		return new EmptyCargo(this);
	}
}
