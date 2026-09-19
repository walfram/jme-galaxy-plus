package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record WeaponsOf(int guns, double caliber, TechLevel techLevel) implements Weapons {
	public WeaponsOf(Weapons other, TechLevel techLevel) {
		this(other.guns(), other.caliber(), techLevel);
	}

	public WeaponsOf(int guns, double caliber) {
		this(guns, caliber, new TechLevel());
	}

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
