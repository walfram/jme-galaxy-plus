package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Industry(double value) {

	public Industry {
		if (value < 0) {
			throw new IllegalArgumentException("Industry value must be positive");
		}
	}

	public Industry(JsonNode source) {
		this(source.doubleValue());
	}
}
