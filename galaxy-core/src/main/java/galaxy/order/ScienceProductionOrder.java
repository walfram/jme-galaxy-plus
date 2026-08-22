package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.ScienceProduction;

public class ScienceProductionOrder implements Order {
	private final Race race;
	private final Planet planet;
	private final Science science;

	public ScienceProductionOrder(Race race, Planet planet, Science science) {
		this.race = race;
		this.planet = planet;
		this.science = science;
	}

	@Override
	public OrderResult modify(GameState state) {
		Race r = state.findRace(race.id());
		Planet p = r.findPlanet(planet.id());

		p.startProduction(new ScienceProduction(science));

		return new OrderResult(true, "started production of %s on %s".formatted(science.name(), planet.id()));
	}
}
