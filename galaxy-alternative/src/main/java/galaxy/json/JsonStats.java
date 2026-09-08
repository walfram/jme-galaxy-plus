package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.planet.Stats;

public class JsonStats implements Stats {
	private final JsonNode src;

	public JsonStats(JsonNode src) {
		this.src = src;
	}

	@Override
	public double size() {
		return src.get("size").asDouble();
	}

	@Override
	public double resources() {
		return src.get("resources").asDouble();
	}
}
