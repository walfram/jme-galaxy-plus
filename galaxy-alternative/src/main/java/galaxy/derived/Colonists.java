package galaxy.derived;

import galaxy.Planet;

public final class Colonists {

	private final Planet planet;

	public Colonists(Planet planet) {
		this.planet = planet;
	}

	public double value() {
		return Math.max(0.0, (planet.population() - planet.size()) / 8.0);
	}

}
