package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Population(double value) {

	public Population {
		if (value < 0) {
			throw new IllegalArgumentException("Population value must be positive");
		}
	}

	public Population(JsonNode source) {
		this(source.doubleValue());
	}
}
