package domain.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import domain.planet.PlanetProperty;

public record Resources(double value) implements PlanetProperty {

	public Resources {
		if (value < 0) {
			throw new IllegalArgumentException("Resources value must be positive");
		}
	}

	public Resources(JsonNode source) {
		this(source.doubleValue());
	}

	public String asString() {
		return String.format("%.2f", value);
	}
}
