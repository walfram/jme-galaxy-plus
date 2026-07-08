package domain.production;

import domain.planet.Planet;

public class MaterialsProduction extends Production {

	public MaterialsProduction(Planet planet) {
		super(planet);
	}

	@Override
	public void execute() {
		double materials = planet.effort().value() * planet.resources().value();
		planet.materials().update(materials);
	}
}
