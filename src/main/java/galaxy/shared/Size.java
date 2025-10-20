package galaxy.shared;

import com.fasterxml.jackson.databind.JsonNode;

public record Size(float value) {
	public Size(JsonNode source) {
		this(source.floatValue());
	}
}
