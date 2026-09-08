package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipType;
import galaxy.ship.Weapons;

public class JsonShipType implements ShipType {
	private final JsonNode src;

	public JsonShipType(JsonNode src) {
		this.src = src;
	}

	@Override
	public double engines() {
		return src.get("engines").asDouble();
	}

	@Override
	public Weapons weapons() {
		return new JsonWeapons(src.get("weapons"));
	}

	@Override
	public double shields() {
		return src.get("shields").asDouble();
	}

	@Override
	public double cargo() {
		return src.get("cargo").asDouble();
	}
}
