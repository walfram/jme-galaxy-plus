package galaxy.core.production;

import galaxy.core.planet.Effort;
import galaxy.core.planet.Industry;
import galaxy.core.planet.Population;
import galaxy.core.planet.Resources;
import galaxy.core.production.result.ModifyPlanetMaterials;

import java.util.List;

public class MaterialsProduction implements Production {

	private double elapsed;

	@Override
	public void advance(double tpf) {
		elapsed = Math.max(1.0, elapsed + tpf);
	}

	@Override
	public boolean isComplete() {
		return elapsed >= 1.0;
	}

	@Override
	public List<ProductionResult> complete(ProductionContext context) {
		double resources = context.planet().prop(Resources.class).value();

		Effort effort = new Effort(
				context.planet().prop(Population.class),
				context.planet().prop(Industry.class)
		);

		return List.of(
				new ModifyPlanetMaterials(effort.value() * resources)
		);
	}
}
