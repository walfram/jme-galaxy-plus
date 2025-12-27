package domain.production;

import domain.production.result.SpawnShips;
import domain.ship.ShipDesign;

import java.util.List;

public class ShipProduction implements Production {

	private final ShipDesign design;
	private final int amount;

	public ShipProduction(int amount, ShipDesign design) {
		this.amount = amount;
		this.design = design;
	}

	@Override
	public void advance(double tpf) {

	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public List<ProductionResult> complete(ProductionContext context) {
		return List.of(
				new SpawnShips(amount, design)
		);
	}
}
