package galaxy.domain.production;

import galaxy.domain.production.result.ModifyPlanetMaterials;
import galaxy.domain.production.result.ModifyTeamTechnology;

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
		return List.of(
				new ModifyPlanetMaterials(100.0)
		);
	}
}
