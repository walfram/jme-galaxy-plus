package domain.production.result;

import domain.production.ProductionContext;
import domain.production.ProductionResult;

public record ModifyPlanetIndustry(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
