package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipType;
import galaxy.ShipTypes;

public final class JsonShipTypes implements ShipTypes {

	private final JsonNode src;

	public JsonShipTypes(JsonNode src) {
		this.src = src;
	}

	@Override
	public int size() {
		return src.size();
	}

	@Override
	public ShipType typeById(String id) {
		return new JsonShipType(src.get(id), id);
	}

}
