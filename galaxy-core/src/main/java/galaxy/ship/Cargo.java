package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Cargo(double size) {
	public Cargo(JsonNode src) {
		this(src.asDouble());
	}
}
