package domain.production.result;

import domain.Entity;
import domain.planet.Materials;
import domain.production.ProductionContext;
import domain.production.ProductionResult;

public record ModifyPlanetMaterials(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {
		Entity planet = context.planet();
		Materials updated = new Materials(planet.prop(Materials.class).value() + delta);
		planet.put(updated);
	}
}
