package galaxy.core.production;

import galaxy.core.Technology;
import galaxy.core.planet.Effort;
import galaxy.core.production.result.ModifyTeamTechnology;

import java.util.List;

public class TechnologyProduction implements Production {

	private static double TECHNOLOGY_RESEARCH_COST = 5000.0;
	private final Technology technology;

	public TechnologyProduction(Technology technology) {
		this.technology = technology;
	}

	@Override
	public void advance(double tpf) {

	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public List<ProductionResult> complete(ProductionContext context) {
		double effort = new Effort(context.planet()).value();
		double delta = effort / TECHNOLOGY_RESEARCH_COST;

		return List.of(
				new ModifyTeamTechnology(context.team(), technology, delta)
		);
	}
}
