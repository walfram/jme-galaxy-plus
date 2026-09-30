package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record CargoBay(double size) implements ShipComponent {

	public CargoBay(JsonNode src) {
		this(src.asDouble());
	}

	public CargoBay() {
		this(0.0);
	}

	@Override
	public double mass() {
		return size;
	}


	public double capacity() {
		return size + size * size / 20.0;
	}

	public double capacity(TechLevel techLevel) {
		return techLevel.value() * capacity();
	}

}
