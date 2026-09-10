package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.ShieldsPower;

public class JsonShiledsPower implements ShieldsPower {
	private final JsonNode src;

	public JsonShiledsPower(JsonNode src) {
		this.src = src;
	}

	@Override
	public Double value() {
		return src.get("shields").asDouble();
	}

	@Override
	public Double mass() {
		return value();
	}
}
