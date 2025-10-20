package galaxy.shared;

import com.fasterxml.jackson.databind.JsonNode;

public record Resources(float value) {
	public Resources(JsonNode source) {
		this(source.floatValue());
	}
}
