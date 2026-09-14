package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Engines(double size) {
	public Engines(JsonNode src) {
		this(src.asDouble());
	}
}
