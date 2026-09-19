package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record ShieldsOf(double size, TechLevel techLevel) implements Shields {
	public ShieldsOf(Shields other, TechLevel techLevel) {
		this(other.size(), techLevel);
	}

	public ShieldsOf(double power) {
		this(power, new TechLevel());
	}

	public ShieldsOf(JsonNode src) {
		this(src.asDouble());
	}

	@Override
	public double mass() {
		return size;
	}
}
