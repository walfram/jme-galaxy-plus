package galaxy.core.production.result;

import galaxy.core.Entity;
import galaxy.core.Technology;
import galaxy.core.production.ProductionContext;
import galaxy.core.production.ProductionResult;
import galaxy.core.TechLevels;

public record ModifyTeamTechnology(Entity team, Technology technology, double delta) implements ProductionResult {

	@Override
	public void update(ProductionContext context) {
		TechLevels techLevels = context.team().prop(TechLevels.class);
		TechLevels updated = techLevels.update(technology, delta);
		context.team().put(updated);
	}

}
