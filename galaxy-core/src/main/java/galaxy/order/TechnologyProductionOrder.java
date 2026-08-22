package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.TechnologyProduction;

public class TechnologyProductionOrder implements Order {
	private final Race race;
	private final Planet planet;
	private final Technology tech;

	public TechnologyProductionOrder(Race race, Planet planet, Technology tech) {
		this.race = race;
		this.planet = planet;
		this.tech = tech;
	}

	@Override
	public OrderResult modify(GameState state) {
		Race r = state.findRace(race.id());
		Planet p = r.findPlanet(planet.id());

		p.startProduction(new TechnologyProduction(tech));

		return new OrderResult(true, "started production of %s on %s".formatted(tech.name(), planet.id()));
	}
}
