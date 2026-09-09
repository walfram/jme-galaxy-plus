package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;

public class JsonPlanet implements Planet {
	private final JsonNode src;

	public JsonPlanet(JsonNode src) {
		this.src = src;
	}

	@Override
	public String id() {
		return src.get("id").asText();
	}

	@Override
	public double x() {
		return src.get("x").asDouble();
	}

	@Override
	public double y() {
		return src.get("y").asDouble();
	}

	@Override
	public double size() {
		return src.get("size").asDouble();
	}

	@Override
	public double resources() {
		return src.get("resources").asDouble();
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

	@Override
	public String owner() {
		return src.has("owner") ? src.path("owner").asText() : null;
	}

}
