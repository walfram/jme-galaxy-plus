package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.MassOf;
import galaxy.ship.Weapons;
import galaxy.ship.WeaponsMass;

public class JsonWeapons implements Weapons {
	private final JsonNode src;

	public JsonWeapons(JsonNode src) {
		this.src = src;
	}

	@Override
	public Integer guns() {
		return src.get("guns").asInt();
	}

	@Override
	public Double caliber() {
		return src.get("caliber").asDouble();
	}

	@Override
	public Double mass() {
		return new WeaponsMass(this).value();
	}
}
