package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Tech;
import galaxy.TechLevels;

public class JsonTechLevels implements TechLevels {
	private final JsonNode src;

	public JsonTechLevels(JsonNode src) {
		this.src = src;
	}

	@Override
	public double engines() {
		return src.get(Tech.ENGINES.name()).asDouble();
	}

	@Override
	public double weapons() {
		return src.get(Tech.WEAPONS.name()).asDouble();
	}

	@Override
	public double shields() {
		return src.get(Tech.SHIELDS.name()).asDouble();
	}

	@Override
	public double cargo() {
		return src.get(Tech.CARGO.name()).asDouble();
	}
}
