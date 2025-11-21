package galaxy.domain.production;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetProperty;

public abstract class Production implements PlanetProperty {

	protected final Planet planet;

	protected Production(Planet planet) {
		this.planet = planet;
	}

	abstract public void execute();

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
