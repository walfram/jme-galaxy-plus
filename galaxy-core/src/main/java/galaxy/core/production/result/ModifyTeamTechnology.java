package galaxy.core.production.result;

import galaxy.core.production.ProductionContext;
import galaxy.core.production.ProductionResult;

public record ModifyTeamTechnology(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
