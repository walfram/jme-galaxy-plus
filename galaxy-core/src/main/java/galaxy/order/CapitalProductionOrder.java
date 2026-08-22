package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.CapitalProduction;

public class CapitalProductionOrder implements Order {
	private final Race race;
	private final Planet planet;

	public CapitalProductionOrder(Race race, Planet planet) {
		this.race = race;
		this.planet = planet;
	}

	@Override
	public OrderResult modify(GameState state) {
		Race r = state.findRace(race.id());
		Planet p = r.findPlanet(planet.id());

		p.startProduction(new CapitalProduction());

		return new OrderResult(true, "started production of capital on %s".formatted(planet.id()));
	}
}
