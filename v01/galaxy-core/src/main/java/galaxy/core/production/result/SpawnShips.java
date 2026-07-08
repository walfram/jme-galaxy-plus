package galaxy.core.production.result;

import galaxy.core.Entity;
import galaxy.core.production.ProductionContext;
import galaxy.core.production.ProductionResult;
import galaxy.core.ship.ShipDesign;

public record SpawnShips(int amount, ShipDesign shipDesign, Entity planet) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
