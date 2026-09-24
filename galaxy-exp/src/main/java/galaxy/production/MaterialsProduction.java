package galaxy.production;

import galaxy.planet.Materials;
import galaxy.planet.Planet;

public class MaterialsProduction implements Production<Materials> {
	private final Planet planet;

	public MaterialsProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public Materials produce() {
		return new Materials(planet.effort().value() * planet.resources().value());
	}
}
