package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipGroup;
import galaxy.ShipType;
import galaxy.TechLevels;
import galaxy.ship.Cargo;
import galaxy.ship.Location;

public class JsonShipGroup implements ShipGroup {
	private final JsonNode src;
	private final ShipType shipType;

	public JsonShipGroup(JsonNode src, ShipType shipType) {
		this.src = src;
		this.shipType = shipType;
	}

	@Override
	public String id() {
		return src.get("id").asText();
	}

	@Override
	public String owner() {
		return src.get("owner").asText();
	}

	@Override
	public int size() {
		return src.get("size").asInt();
	}

	@Override
	public ShipType shipType() {
		return shipType;
	}

	@Override
	public TechLevels techLevels() {
		return new JsonTechLevels(src.get("tech"));
	}

	@Override
	public Location location() {
		return new JsonLocation(src.get("location"));
	}

	@Override
	public Cargo cargo() {
		return new JsonCargo(src.get("cargo"), shipType().cargoSize());
	}

	@Override
	public ShipGroup unloaded() {
		return null;
	}
}
