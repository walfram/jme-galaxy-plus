package galaxy.production;

import galaxy.Production;
import galaxy.Science;
import galaxy.context.GameContext;
import galaxy.planet.Materials;

public final class ResearchScienceProduction implements Production {

	public ResearchScienceProduction(Science science) {}

	@Override
	public void produce(GameContext context) {

	}

	@Override
	public Materials cancel() {
		return null;
	}
}
