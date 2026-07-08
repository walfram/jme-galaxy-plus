package galaxy.core.production.result;

import galaxy.core.Entity;
import galaxy.core.planet.Materials;
import galaxy.core.production.ProductionContext;
import galaxy.core.production.ProductionResult;

public record ModifyPlanetMaterials(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {
		Entity planet = context.planet();
		Materials updated = new Materials(planet.prop(Materials.class).value() + delta);
		planet.put(updated);
	}
}
