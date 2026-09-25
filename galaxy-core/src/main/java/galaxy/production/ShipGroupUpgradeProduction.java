package galaxy.production;

import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.planet.Materials;
import galaxy.ship.ShipGroup;

public final class ShipGroupUpgradeProduction implements Production {

	public ShipGroupUpgradeProduction(ShipGroup group) {}

	@Override
	public void produce(GameContext context) {

	}

	@Override
	public Materials cancel() {
		return null;
	}
}
