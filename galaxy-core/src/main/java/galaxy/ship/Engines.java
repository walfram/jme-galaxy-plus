package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Engines(double size) implements ShipComponent {

	public Engines(JsonNode src) {
		this(src.asDouble());
	}

	public Engines() {
		this(0.0);
	}

	@Override
	public double mass() {
		return size;
	}

}
