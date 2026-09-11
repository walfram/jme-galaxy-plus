package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class Industry {

	private double value;

	public Industry(double value) {
		this.value = value;
	}

	public Industry(JsonNode props) {
		this(props.path("industry").asDouble());
	}

	public Industry() {
		this(0.0);
	}

	public double value() {
		return value;
	}

	public void update(double delta) {
		this.value += delta;
	}

	public void serializeTo(ObjectNode props) {
		props.put("industry", value);
	}
}
