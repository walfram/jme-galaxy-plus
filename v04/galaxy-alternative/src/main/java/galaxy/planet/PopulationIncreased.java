package galaxy.planet;

import galaxy.Planet;

public class PopulationIncreased {
	private final Planet source;
	private final Colonists requested;

	public PopulationIncreased(Planet source, Colonists requested) {
		this.source = source;
		this.requested = requested;
	}

	public double value() {
		return source.population() + requested.value() * 8.0;
	}
}
