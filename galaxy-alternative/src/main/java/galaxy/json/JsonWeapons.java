package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.Weapons;

public class JsonWeapons implements Weapons {
	private final JsonNode src;

	public JsonWeapons(JsonNode src) {
		this.src = src;
	}

	@Override
	public int guns() {
		return src.get("guns").asInt();
	}

	@Override
	public double caliber() {
		return src.get("caliber").asDouble();
	}
}
