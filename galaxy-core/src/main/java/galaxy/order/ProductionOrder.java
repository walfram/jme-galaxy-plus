package galaxy.order;

import galaxy.core.*;

public class ProductionOrder implements Order {
	private final Race race;
	private final Planet planet;
	private final Production production;

	public ProductionOrder(Race race, Planet planet, Production production) {
		this.race = race;
		this.planet = planet;
		this.production = production;
	}

	@Override
	public OrderResult modify(GameState state) {
		Race r = state.findRace(race.id());
		Planet p = r.findPlanet(planet.id());

		p.startProduction(production);

		return new OrderResult(true, "started production %s on %s for %s".formatted(production.getClass().getSimpleName(), planet.id(), race.id()));
	}
}
