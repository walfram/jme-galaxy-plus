package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record Shields(double size) implements ShipComponent {
	public Shields(JsonNode src) {
		this(src.asDouble());
	}

	public Shields() {
		this(0.0);
	}

	@Override
	public double mass() {
		return size;
	}
}
