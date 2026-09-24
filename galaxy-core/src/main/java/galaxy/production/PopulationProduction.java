package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;

public class PopulationProduction implements Production {
	private final Planet planet;

	public PopulationProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void produce(GameContext context) {
		throw new UnsupportedOperationException("Population production is not implemented yet");
	}
}
