package galaxy.order;

import galaxy.Order;
import galaxy.Planet;
import galaxy.Production;
import galaxy.Race;
import galaxy.context.GameContext;

public final class StartProduction implements Order {

	private final Race race;
	private final Planet planet;
	private final Production production;

	public StartProduction(Race race, Planet planet, Production production) {
		this.race = race;
		this.planet = planet;
		this.production = production;
	}

	@Override
	public void applyTo(GameContext context) {

	}
}
