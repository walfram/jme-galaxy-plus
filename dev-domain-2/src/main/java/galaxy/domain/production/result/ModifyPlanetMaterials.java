package galaxy.domain.production.result;

import galaxy.domain.production.ProductionContext;
import galaxy.domain.production.ProductionResult;

public record ModifyPlanetMaterials(double delta) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
