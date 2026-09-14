package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Shields(double size) {
	public Shields(JsonNode src) {
		this(src.asDouble());
	}
}
