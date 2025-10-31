package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Size(double value) {

	public Size {
		if (value < 0) {
			throw new IllegalArgumentException("Size value must be positive");
		}
	}

	public Size(JsonNode source) {
		this(source.doubleValue());
	}
}
