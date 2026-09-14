package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Id;
import galaxy.Race;
import galaxy.ShipTypes;
import galaxy.TechLevels;

public class JsonRace implements Race {
	private final String id;
	private final JsonNode src;

	public JsonRace(String id, JsonNode src) {
		this.id = id;
		this.src = src;
	}

	@Override
	public Id id() {
		return new Id(id);
	}

	@Override
	public TechLevels techLevels() {
		return new JsonTechLevels(src.get("techLevels"));
	}

	@Override
	public ShipTypes shipTypes() {
		return new JsonShipTypes(src.get("shipTypes"));
	}
}
