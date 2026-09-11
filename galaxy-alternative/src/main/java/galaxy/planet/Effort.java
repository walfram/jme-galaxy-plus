package galaxy.planet;

import galaxy.Planet;

public final class Effort {
	private final Planet planet;

	public Effort(Planet planet) {
		this.planet = planet;
	}

	public double value() {
		return 0.75 * planet.industry() + 0.25 * planet.population();
	}
}
