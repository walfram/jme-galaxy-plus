package galaxy.domain.planet;

import com.fasterxml.jackson.databind.JsonNode;

public record Resources(double value) {

	public Resources {
		if (value < 0) {
			throw new IllegalArgumentException("Resources value must be positive");
		}
	}

	public Resources(JsonNode source) {
		this(source.doubleValue());
	}
}
