package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.Location;

public class JsonLocation implements Location {

	private final JsonNode src;

	public JsonLocation(JsonNode src) {
		this.src = src;
	}
}
