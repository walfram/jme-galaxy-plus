package galaxy.domain.production;

import galaxy.domain.planet.Planet;

public abstract class Production {

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
