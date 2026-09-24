package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public record IndustryOf(double value) implements Industry {
	public IndustryOf(JsonNode src) {
		this(src.asDouble());
	}

	public IndustryOf() {
		this(0.0);
	}
}
