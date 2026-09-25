package galaxy.production;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Production;
import galaxy.Tech;
import galaxy.context.GameContext;
import galaxy.context.Planets;
import galaxy.planet.Materials;
import galaxy.planet.PlanetId;

public final class ResearchTechProduction implements Production {

	private final Planet planet;
	private final Tech tech;

	public ResearchTechProduction(Planet planet, Tech tech) {
		this.planet = planet;
		this.tech = tech;
	}

	public ResearchTechProduction(JsonNode src, Planets planets) {
		this(
				planets.findById(new PlanetId(src)),
				Tech.valueOf(src.required("target").asText())
		);
	}

	@Override
	public void produce(GameContext context) {
		double delta = planet.effort().value() / 5000.0;
		planet.owner().orElseThrow().techLevels().upgradeTo(tech, delta);
	}

	@Override
	public Materials cancel() {
		return new Materials();
	}
}
