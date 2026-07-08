package galaxy.core.planet;

import galaxy.core.Entity;

public record Effort(Population population, Industry industry) {
	public Effort(Entity planet) {
		this(
				planet.prop(Population.class),
				planet.prop(Industry.class)
		);
	}

	public double value() {
		return 0.75 * industry.value() + 0.25 * population.value();
	}
}
