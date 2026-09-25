package galaxy.production;

import galaxy.Planet;
import galaxy.Production;
import galaxy.context.GameContext;
import galaxy.planet.Capital;
import galaxy.planet.Materials;

public final class CapitalProduction implements Production {

	private static final double cost = 5.0;

	private final Planet planet;

	public CapitalProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public void produce(GameContext context) {
		double mat = planet.materials().quantity();
		double effort = planet.effort().value();

		double produced;
		double extra = effort - mat * cost;

		if (extra >= 0.0) {
			double res = planet.resources().value();
			produced = mat + extra * res / (cost * res + 1.0);
			planet.withdrawMaterials(new Materials(mat));
		} else {
			produced = effort / cost;
			planet.withdrawMaterials(new Materials(produced));
		}

		planet.unloadCapital(new Capital(produced));
	}

	@Override
	public Materials cancel() {
		return new Materials();
	}
}
