package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.Cargo;
import galaxy.ship.CargoSize;
import galaxy.ship.EmptyCargo;

public class JsonCargoSize implements CargoSize {
	private final JsonNode src;

	public JsonCargoSize(JsonNode src) {
		this.src = src;
	}

	@Override
	public Double value() {
		return src.get("cargo").asDouble();
	}

	@Override
	public boolean isPresent() {
		return mass() > 0.0;
	}

	@Override
	public Double mass() {
		return value();
	}

	@Override
	public Cargo toCargo() {
		return mass() > 0.0 ? new JsonCargo(src.get("cargo"), this) : new EmptyCargo(this);
	}
}
