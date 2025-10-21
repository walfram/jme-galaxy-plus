package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Population(float value) {
	public Population(JsonNode source) {
		this(source.floatValue());
	}
}
