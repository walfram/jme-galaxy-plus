package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.MaterialsProduction;

public class MaterialsProductionOrder implements Order {
	private final Race race;
	private final Planet planet;

	public MaterialsProductionOrder(Race race, Planet planet) {
		this.race = race;
		this.planet = planet;
	}

	@Override
	public OrderResult modify(GameState state) {
		Race r = state.findRace(race.id());
		Planet p = r.findPlanet(planet.id());

		p.startProduction(new MaterialsProduction());

		return new OrderResult(true, "started production of materials on %s".formatted(planet.id()));
	}
}
