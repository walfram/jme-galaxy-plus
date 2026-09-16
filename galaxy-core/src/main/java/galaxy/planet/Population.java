package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

public final class Population {

	private double value;

	public Population(double value) {
		this.value = value;
	}

	public Population() {
		this(0.0);
	}

	public Population(JsonNode src) {
		this(src.asDouble());
	}

	public double value() {
		return value;
	}

	public void add(Population other) {
		value += other.value();
	}

	public void decrease(Colonists withdrawn) {
		value -= withdrawn.toPopulation().value();
	}
}
