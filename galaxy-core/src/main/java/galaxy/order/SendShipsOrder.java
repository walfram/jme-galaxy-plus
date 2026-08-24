package galaxy.order;

import galaxy.core.*;

public class SendShipsOrder implements Order {

	private final Race race;
	private final ShipGroup shipGroup;
	private final Planet source;
	private final Planet destination;

	public SendShipsOrder(Race race, ShipGroup shipGroup, Planet source, Planet destination) {
		this.race = race;
		this.shipGroup = shipGroup;
		this.source = source;
		this.destination = destination;
	}

	@Override
	public OrderResult modify(GameState state) {
		// find ship group
//		ShipGroup group = state.shipGroup(shipGroup.id());
		ShipGroup group = race.shipGroups().find(shipGroup.id());

		// check if this group's owner is correct
		Race r = state.findRace(race.id());
		if (!r.equals(group.owner())) {
			return new OrderResult(false, "race %s is not the owner of ship group %s".formatted(race.id(), shipGroup.id()));
		}

		// check if group at defined planet
		Planet planet = state.findPlanet(source.id());
		if (!planet.equals(group.currentPlanet())) {
			return new OrderResult(false, "group %s is not at planet %s".formatted(race.id(), source.id()));
		}

		// check if destination can be reached
		if (!shipGroup.canFlyTo(destination)) {
			return new OrderResult(false, "destination planet %s is too far for ship group %s".formatted(destination.id(), group.id()));
		}

		shipGroup.flyTo(destination);

		return new OrderResult(true, "sent ship group %s to planet %s".formatted(group.id(), destination.id()));
	}
}
