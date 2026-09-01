package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import galaxy.Production;
import galaxy.Race;
import galaxy.production.ShipGroupProduction;
import galaxy.production.ShipGroupUpgradeProduction;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

import java.util.List;
import java.util.Objects;

public class DefineShipTypeOrder implements Order {
	private final Race race;
	private final ShipType shipType;

	public DefineShipTypeOrder(Race race, ShipType shipType) {
		this.race = race;
		this.shipType = shipType;
	}

	@Override
	public void modify(GameContext context) {
		List<ShipGroup> groups = context.findShipGroups(race, shipType);
		if (!groups.isEmpty()) {
			throw new IllegalArgumentException("Cannot replace ShipType %s because there are ShipGroups of that type (%s)".formatted(shipType.name(), groups.size()));
		}

		List<Production> productions = context.findProductions(race);

		boolean isBuilding = productions.stream()
				.filter(p -> p instanceof ShipGroupProduction)
				.map(p -> ((ShipGroupProduction) p))
				.anyMatch(p -> Objects.equals(p.shipType().name(), shipType.name()));

		boolean isUpgrading = productions.stream()
				.filter(p -> p instanceof ShipGroupUpgradeProduction)
				.map(p -> ((ShipGroupUpgradeProduction) p))
				.anyMatch(p -> Objects.equals(p.shipGroup().shipType().name(), shipType.name()));

		if (isBuilding || isUpgrading) {
			throw new IllegalArgumentException("Cannot replace ShipType %s because there are productions of that type".formatted(shipType.name()));
		}

		context.createShipType(race, shipType);
	}
}
