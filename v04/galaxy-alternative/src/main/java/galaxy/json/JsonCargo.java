package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.CargoType;
import galaxy.ship.Cargo;
import galaxy.ship.CargoSize;

public class JsonCargo implements Cargo {
	private final JsonNode src;
	private final CargoSize cargoSize;

	public JsonCargo(JsonNode src, CargoSize cargoSize) {
		this.src = src;
		this.cargoSize = cargoSize;
	}

	@Override
	public CargoType type() {
		return CargoType.valueOf(src.get("type").asText());
	}

	@Override
	public Double quantity() {
		return src.get("quantity").asDouble();
	}

	@Override
	public Double mass() {
		return quantity() / cargoSize.value();
	}
}
