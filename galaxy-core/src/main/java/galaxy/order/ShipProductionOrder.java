package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.ShipProduction;

public class ShipProductionOrder implements Order {
	private final Race race;
	private final Planet planet;
	private final ShipType shipType;

	public ShipProductionOrder(Race race, Planet planet, ShipType shipType) {
		this.race = race;
		this.planet = planet;
		this.shipType = shipType;
	}

	@Override
	public OrderResult modify(GameState state) {
		Race r = state.findRace(race.id());
		Planet p = r.findPlanet(planet.id());

		p.startProduction(new ShipProduction(shipType));

		return new OrderResult(true, "started production of %s on %s".formatted(shipType.name(), planet.id()));
	}
}
