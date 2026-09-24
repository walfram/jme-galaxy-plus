package galaxy.production;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Production;
import galaxy.Tech;
import galaxy.context.GameContext;

public final class ResearchTechProduction implements Production {
	public ResearchTechProduction(Tech tech) {

	}

	public ResearchTechProduction(JsonNode src) {
		this(Tech.valueOf(src.get("type").asText()));
	}

	@Override
	public void produce(GameContext context) {

	}
}
