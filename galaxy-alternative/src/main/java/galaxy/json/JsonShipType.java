package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipType;
import galaxy.ship.*;

public class JsonShipType implements ShipType {
	private final JsonNode src;

	public JsonShipType(JsonNode src) {
		this.src = src;
	}

	@Override
	public String name() {
		return src.get("name").asText();
	}

	@Override
	public EngineSize engineSize() {
		return new JsonEngineSize(src);
	}

	@Override
	public Weapons weapons() {
		return new JsonWeapons(src.get("weapons"));
	}

	@Override
	public ShieldsPower shieldsPower() {
		return new JsonShiledsPower(src);
	}

	@Override
	public CargoSize cargoSize() {
		return new JsonCargoSize(src);
	}

	@Override
	public Double mass() {
		return new ShipTypeMass(this).value();
	}
}
