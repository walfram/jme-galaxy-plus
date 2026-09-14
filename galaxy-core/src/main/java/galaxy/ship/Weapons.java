package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Weapons(int guns, double caliber) {
	public Weapons(JsonNode src) {
		this(
				src.get("guns").asInt(),
				src.get("caliber").asDouble()
		);
	}
}
