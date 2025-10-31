package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Size(float value) {

	public Size {
		if (value < 0f) {
			throw new IllegalArgumentException("Size value must be positive");
		}
	}

	public Size(JsonNode source) {
		this(source.floatValue());
	}
}
