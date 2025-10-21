package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;

public record Industry(float value) {
	public Industry(JsonNode source) {
		this(source.floatValue());
	}
}
