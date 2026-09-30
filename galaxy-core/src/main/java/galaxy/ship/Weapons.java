package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Weapons(int guns, double caliber) implements ShipComponent {
	public Weapons(JsonNode src) {
		this(
				src.get("guns").asInt(),
				src.get("caliber").asDouble()
		);
	}

	public Weapons() {
		this(0, 0.0);
	}

	@Override
	public double mass() {
		return caliber * (guns + 1) / 2.0;
	}
}
