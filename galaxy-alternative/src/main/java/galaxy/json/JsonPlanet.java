package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.planet.Props;
import galaxy.planet.State;
import galaxy.planet.Stats;
import galaxy.planet.Transform;

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
	public Transform transform() {
		return new JsonTransform(src.get("transform"));
	}

	@Override
	public Stats stats() {
		return new JsonStats(src.get("stats"));
	}

	@Override
	public Props props() {
		return new JsonProps(src.get("props"));
	}

	@Override
	public State state() {
		return new JsonState(src.path("state"));
	}
}
