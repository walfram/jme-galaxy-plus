package galaxy.domain.production.result;

import galaxy.domain.production.ProductionContext;
import galaxy.domain.production.ProductionResult;
import galaxy.domain.ship.ShipDesign;

public record SpawnShips(int amount, ShipDesign shipDesign) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
