package galaxy.race;

import com.fasterxml.jackson.databind.JsonNode;

public record RaceId(String value) {
	public RaceId(JsonNode src) {
		this(src.asText());
	}
}
