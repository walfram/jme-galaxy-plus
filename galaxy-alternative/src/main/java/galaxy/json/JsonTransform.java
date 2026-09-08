package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.planet.Transform;

public class JsonTransform implements Transform {
	private final JsonNode src;

	public JsonTransform(JsonNode src) {
		this.src = src;
	}

	@Override
	public double x() {
		return src.get("x").asDouble();
	}

	@Override
	public double y() {
		return src.get("y").asDouble();
	}
}
