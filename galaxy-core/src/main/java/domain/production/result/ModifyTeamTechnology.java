package domain.production.result;

import domain.production.ProductionContext;
import domain.production.ProductionResult;

public record ModifyTeamTechnology(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
