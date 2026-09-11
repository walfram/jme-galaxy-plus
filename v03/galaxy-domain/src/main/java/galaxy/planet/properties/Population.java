package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class Population {
	private double value;

	public Population(JsonNode props) {
		this(props.path("population").asDouble());
	}

	public Population(double value) {
		this.value = value;
	}

	public Population() {
		this(0.0);
	}

	public double value() {
		return value;
	}

	public void update(double delta) {
		this.value += delta;
	}

	public void serializeTo(ObjectNode props) {
		props.put("population", value);
	}
}
