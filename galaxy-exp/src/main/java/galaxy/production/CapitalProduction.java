package galaxy.production;

import galaxy.planet.Capital;
import galaxy.planet.Materials;
import galaxy.planet.Planet;

public class CapitalProduction implements Production<Capital> {

	private static final double cost = 5.0;

	private final Planet planet;

	public CapitalProduction(Planet planet) {
		this.planet = planet;
	}

	@Override
	public Capital produce() {
		double mat = planet.materials().quantity();
		double effort = planet.effort().value();

		double produced;
		double extra = effort - mat * cost;

		if (extra >= 0.0) {
			double res = planet.resources().value();
			produced = mat + extra * res / (cost * res + 1.0);
			// planet.addMaterials(-mat);
			planet.remove(new Materials(mat));
		} else {
			produced = effort / cost;
			// planet.addMaterials(-produced);
			planet.remove(new Materials(produced));
		}

		// planet.addIndustry(produced);
		// planet.unload(new Capital(produced));
		return new Capital(produced);
	}
}
