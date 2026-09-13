package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public record Size(double value) {
	public Size(JsonNode src) {
		this(src.asDouble());
	}
}
