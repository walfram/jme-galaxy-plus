package galaxy.derived;

import galaxy.Planet;

public final class Capital {
	private final Planet planet;

	public Capital(Planet planet) {
		this.planet = planet;
	}

	public double value() {
		return Math.max(0.0, planet.industry() - planet.size());
	}
}
