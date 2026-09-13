package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public record Coordinates(double x, double y) {
	public Coordinates(JsonNode src) {
		this(
				src.get("x").asDouble(),
				src.get("y").asDouble()
		);
	}
}
