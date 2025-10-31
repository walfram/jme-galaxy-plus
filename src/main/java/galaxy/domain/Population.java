package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public final class Population {

	private double value;

	public Population(double value) {
		if (value < 0) {
			throw new IllegalArgumentException("Population value must be positive");
		}

		this.value = value;
	}

	public Population(JsonNode source) {
		this(source.doubleValue());
	}

	public void update(double delta) {
		this.value = Math.max(0, this.value + delta);
	}

	public double value() {
		return value;
	}

}
