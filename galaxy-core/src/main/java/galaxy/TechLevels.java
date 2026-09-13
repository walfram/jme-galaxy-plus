package galaxy;

import com.fasterxml.jackson.databind.JsonNode;

public record TechLevels(double engines, double weapons, double shields, double cargo) {

	public TechLevels(JsonNode src) {
		this(
				src.get("ENGINES").asDouble(),
				src.get("WEAPONS").asDouble(),
				src.get("SHIELDS").asDouble(),
				src.get("CARGO").asDouble()
		);
	}

}
