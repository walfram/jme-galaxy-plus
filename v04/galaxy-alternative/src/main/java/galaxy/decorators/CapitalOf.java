package galaxy.decorators;

import galaxy.Planet;
import galaxy.planet.Capital;

public class CapitalOf implements Capital {
	private final Planet source;

	public CapitalOf(Planet	source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return Math.max(0.0, source.industry() - source.size());
	}
}
