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

		if (p.owner() != null && !p.owner().equals(r)) {
			return new OrderResult(false, "planet %s is not owned by %s".formatted(planet.id(), race.id()));
		}

		boolean started = p.startProduction(production);
		if (!started) {
			return new OrderResult(false, "cannot start production %s on %s".formatted(production.getClass().getSimpleName(), planet.id()));
		}

		return new OrderResult(true, "started production %s on %s for %s".formatted(production.getClass().getSimpleName(), planet.id(), race.id()));
	}
}
