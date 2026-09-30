package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record EnginesOf(double size) implements Engines {

	public EnginesOf(JsonNode src) {
		this(src.asDouble());
	}

	@Override
	public double mass() {
		return size;
	}

}
