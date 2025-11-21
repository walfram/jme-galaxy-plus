package galaxy.domain.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.domain.planet.PlanetProperty;

public record Size(double value) implements PlanetProperty {

	public Size {
		if (value < 0) {
			throw new IllegalArgumentException("Size value must be positive");
		}
	}

	public Size(JsonNode source) {
		this(source.doubleValue());
	}
}
