package galaxy.core.phase;

import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.Phase;
import galaxy.core.team.TeamRef;
import galaxy.core.order.ProductionOrder;
import galaxy.core.planet.Planet;
import galaxy.core.production.Production;
import galaxy.core.production.ProductionContext;
import galaxy.core.production.ProductionResult;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class ProductionPhase implements Phase {

	private static final Logger logger = getLogger(ProductionPhase.class);

	private final Context galaxy;

	public ProductionPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> withProductionOrders = galaxy.query(List.of(Planet.class, ProductionOrder.class));
		logger.debug("planet with production orders {}", withProductionOrders.size());

		Map<TeamRef, Entity> teams = galaxy.teams();

		for (Entity planet : withProductionOrders) {
			Production production = planet.prop(ProductionOrder.class).production();
			production.advance(tpf);

			if (production.isComplete()) {
				logger.debug("production complete for {}", planet);

				Entity team = teams.get(planet.prop(TeamRef.class));

				ProductionContext context = new ProductionContext(planet, team);
				List<ProductionResult> results = production.complete(context);
				planet.remove(ProductionOrder.class);

				for (ProductionResult result : results) {
					logger.debug("production result {}", result);
					result.update(context);
				}
			}
		}
	}
}
