package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Industry(float value) {

	public Industry {
		if (value < 0f) {
			throw new IllegalArgumentException("Industry value must be positive");
		}
	}

	public Industry(JsonNode source) {
		this(source.floatValue());
	}
}
