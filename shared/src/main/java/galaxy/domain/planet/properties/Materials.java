package galaxy.domain.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.domain.planet.PlanetProperty;

public class Materials implements PlanetProperty {

	private double value;

	public Materials(double value) {
		this.value = value;
	}

	public Materials(JsonNode json) {
		this(json.doubleValue());
	}

	public double value() {
		return value;
	}

	public void update(double delta) {
		this.value = Math.max(0.0, value + delta);
	}
}
