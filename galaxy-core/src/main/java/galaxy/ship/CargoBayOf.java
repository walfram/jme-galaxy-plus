package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record CargoBayOf(double size) implements CargoBay {

	public CargoBayOf(JsonNode src) {
		this(src.asDouble());
	}

	@Override
	public double mass() {
		return size;
	}


	@Override
	public double capacity() {
		return size + size * size / 20.0;
	}

	@Override
	public double capacity(TechLevel techLevel) {
		return techLevel.value() * capacity();
	}

}
