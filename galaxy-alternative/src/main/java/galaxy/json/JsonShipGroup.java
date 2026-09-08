package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipGroup;

public class JsonShipGroup implements ShipGroup {
	private final JsonNode src;

	public JsonShipGroup(JsonNode src) {
		this.src = src;
	}

	@Override
	public String owner() {
		return src.get("owner").asText();
	}
}
