package galaxy.production;

import galaxy.planet.Planet;
import galaxy.ships.TechLevel;

public class TechLevelProduction implements Production<TechLevel> {
	private final Planet planet;
	private final TechLevel techLevel;

	public TechLevelProduction(Planet planet, TechLevel techLevel) {
		this.planet = planet;
		this.techLevel = techLevel;
	}

	@Override
	public TechLevel produce() {
		double delta = planet.effort().value() / 5000.0;
		return new TechLevel(techLevel.value() + delta);
	}
}
