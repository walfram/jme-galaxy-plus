package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipGroups;

public class JsonShipGroups implements ShipGroups {
	private final JsonNode src;

	public JsonShipGroups(JsonNode src) {
		this.src = src;
	}

	@Override
	public int size() {
		return src.size();
	}
}
