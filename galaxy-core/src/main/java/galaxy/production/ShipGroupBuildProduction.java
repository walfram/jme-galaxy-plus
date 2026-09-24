package galaxy.production;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.ship.ShipType;

import java.util.Objects;

public final class ShipGroupBuildProduction implements Production {
	private final String shipType;

	public ShipGroupBuildProduction(String shipType) {
		this.shipType = shipType;
	}

	public ShipGroupBuildProduction(ShipType shipType) {
		this(shipType.toString());
	}

	public ShipGroupBuildProduction(JsonNode src) {
		this("todo");
	}

	@Override
	public void produce(GameContext context) {

	}
}
