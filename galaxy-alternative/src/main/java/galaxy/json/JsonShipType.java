package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ShipType;
import galaxy.ship.*;

public class JsonShipType implements ShipType {
	private final JsonNode src;
	private final String name;

	public JsonShipType(JsonNode src, String name) {
		this.src = src;
		this.name = name;
	}

	@Override
	public String name() {
		return name;
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
