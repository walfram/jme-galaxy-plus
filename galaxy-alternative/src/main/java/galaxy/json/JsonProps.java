package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.planet.Props;

public class JsonProps implements Props {
	private final JsonNode src;

	public JsonProps(JsonNode src) {
		this.src = src;
	}

	@Override
	public double industry() {
		return src.path("industry").asDouble();
	}

	@Override
	public double population() {
		return src.path("population").asDouble();
	}

	@Override
	public double materials() {
		return src.path("materials").asDouble();
	}

	@Override
	public String name() {
		return src.get("name").asText();
	}
}
