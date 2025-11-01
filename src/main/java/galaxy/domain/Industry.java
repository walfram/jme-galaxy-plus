package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public final class Industry {

	public double value;

	public Industry(double value) {
		if (value < 0) {
			throw new IllegalArgumentException("Industry value must be positive");
		}

		this.value = value;
	}

	public Industry(JsonNode source) {
		this(source.doubleValue());
	}

	public double value() {
		return value;
	}

	public void update(double delta) {
		this.value = Math.max(0.0, this.value + delta);
	}
}
