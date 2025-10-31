package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Population(float value) {

	public Population {
		if (value < 0f) {
			throw new IllegalArgumentException("Population value must be positive");
		}
	}

	public Population(JsonNode source) {
		this(source.floatValue());
	}
}
