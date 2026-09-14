package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.EngineSize;

public class JsonEngineSize implements EngineSize {
	private final JsonNode src;

	public JsonEngineSize(JsonNode src) {
		this.src = src;
	}

	@Override
	public Double value() {
		return src.get("engines").asDouble();
	}

	@Override
	public Double mass() {
		return value();
	}
}
