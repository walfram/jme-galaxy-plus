package domain.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import domain.planet.PlanetProperty;

public record Size(double value) implements PlanetProperty {

	public Size {
		if (value < 0) {
			throw new IllegalArgumentException("Size value must be positive");
		}
	}

	public Size(JsonNode source) {
		this(source.doubleValue());
	}

	public String asString() {
		return String.format("%.2f", value);
	}
}
