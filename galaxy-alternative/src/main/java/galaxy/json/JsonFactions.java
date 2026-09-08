package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Factions;

public class JsonFactions implements Factions {
	private final JsonNode src;

	public JsonFactions(JsonNode src) {
		this.src = src;
	}

	@Override
	public int size() {
		return src.size();
	}
}
