package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record WeaponsOf(int guns, double caliber) implements Weapons {
	public WeaponsOf(JsonNode src) {
		this(
				src.get("guns").asInt(),
				src.get("caliber").asDouble()
		);
	}

	@Override
	public double mass() {
		return caliber * (guns + 1) / 2.0;
	}
}
