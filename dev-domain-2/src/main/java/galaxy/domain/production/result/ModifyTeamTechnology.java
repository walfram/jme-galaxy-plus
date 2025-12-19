package galaxy.domain.production.result;

import galaxy.domain.Entity;
import galaxy.domain.planet.Materials;
import galaxy.domain.production.ProductionContext;
import galaxy.domain.production.ProductionResult;

public record ModifyTeamTechnology(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {
		Entity planet = context.planet();
		Materials updated = new Materials(planet.prop(Materials.class).value() + delta);
		planet.put(updated);
	}
}
