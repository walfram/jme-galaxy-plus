package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planets;

public class JsonPlanets implements Planets {
	private final JsonNode src;

	public JsonPlanets(JsonNode src) {
		this.src = src;
	}

	@Override
	public int size() {
		return src.size();
	}
}
