package galaxy.planet;

import galaxy.Cargo;

public record Colonists(double quantity) implements Cargo {
	public Colonists(Population population, Size size) {
		this(
				Math.max(0.0, population.value() - size.value()) / 8.0
		);
	}

	public double populationValue() {
		return quantity * 8.0;
	}
}
