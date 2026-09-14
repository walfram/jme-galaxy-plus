package galaxy.ship;

import galaxy.Scalar;

public record EngineSizeOf(Double value) implements EngineSize {

	public EngineSizeOf {
		if (value == null) {
			throw new IllegalArgumentException("Engine size cannot be null");
		}
	}

	public EngineSizeOf(Scalar<Double> source) {
		this(source.value());
	}

	@Override
	public Double mass() {
		return value();
	}
}
