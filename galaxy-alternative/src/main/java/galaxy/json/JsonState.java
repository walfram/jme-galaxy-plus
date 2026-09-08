package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.planet.Production;
import galaxy.planet.State;

public class JsonState implements State {
	private final JsonNode src;

	public JsonState(JsonNode src) {
		this.src = src;
	}

	@Override
	public String owner() {
		return src.has("owner") ? src.get("owner").asText() : null;
	}

	@Override
	public Production production() {
		return src.has("production") ? new JsonProduction(src.path("production")) : null;
	}
}
