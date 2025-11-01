package galaxy.domain.production;

import galaxy.domain.Planet;

public class MaterialsProduction extends Production {

	protected MaterialsProduction(Planet planet) {
		super(planet);
	}

	@Override
	public void execute() {
		double materials = planet.effort().value() * planet.resources().value();
		planet.materials().update(materials);
	}
}
