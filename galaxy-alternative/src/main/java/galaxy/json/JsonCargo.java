package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.CargoType;
import galaxy.ship.Cargo;

public class JsonCargo implements Cargo {
	private final JsonNode src;

	public JsonCargo(JsonNode src) {
		this.src = src;
	}

	@Override
	public CargoType type() {
		return CargoType.valueOf(src.get("type").asText());
	}

	@Override
	public double amountPerShip() {
		return src.get("amountPerShip").asDouble();
	}
}
