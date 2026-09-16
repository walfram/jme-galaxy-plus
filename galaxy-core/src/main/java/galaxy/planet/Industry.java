package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public final class Industry {
	private double value;

	public Industry() {
		this(0.0);
	}

	public Industry(double value) {
		this.value = Math.abs(value);
	}

	public Industry(JsonNode src) {
		this(src.asDouble());
	}

	public void add(Industry other) {
		value += other.value();
	}

	public double value() {
		return value;
	}

	public void decrease(double quantity) {
		this.value -= Math.abs(quantity);
	}
}
