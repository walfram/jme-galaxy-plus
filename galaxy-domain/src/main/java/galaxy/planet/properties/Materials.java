package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class Materials {

	private double value;

	public Materials(JsonNode props) {
		this(props.path("materials").asDouble());
	}

	public Materials(double value) {
		this.value = value;
	}

	public Materials() {
		this(0.0);
	}

	public double value() {
		return value;
	}

	public void update(double delta) {
		this.value += delta;
	}

	public void serializeTo(ObjectNode props) {
		props.put("materials", value);
	}
}
