package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public record Resources(double value) {
	public Resources(JsonNode src) {
		this(src.asDouble());
	}
}
