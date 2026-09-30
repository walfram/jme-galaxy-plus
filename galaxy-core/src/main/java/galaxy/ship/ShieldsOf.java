package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record ShieldsOf(double size) implements Shields {
	public ShieldsOf(JsonNode src) {
		this(src.asDouble());
	}

	@Override
	public double mass() {
		return size;
	}
}
