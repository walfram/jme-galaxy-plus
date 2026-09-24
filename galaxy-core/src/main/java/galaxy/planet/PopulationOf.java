package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public record PopulationOf(double value) implements Population {
	public PopulationOf(JsonNode src) {
		this(src.asDouble());
	}

	public PopulationOf() {
		this(0.0);
	}
}
