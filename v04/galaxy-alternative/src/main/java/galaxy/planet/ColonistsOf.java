package galaxy.planet;

import galaxy.Planet;

public final class ColonistsOf implements Colonists {

	private final Planet source;

	public ColonistsOf(Planet source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return Math.max(0.0, (source.population() - source.size()) / 8.0);
	}

}
