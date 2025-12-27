package domain.production.result;

import domain.production.ProductionContext;
import domain.production.ProductionResult;
import domain.ship.ShipDesign;

public record SpawnShips(int amount, ShipDesign shipDesign) implements ProductionResult {
	@Override
	public void update(ProductionContext context) {

	}
}
