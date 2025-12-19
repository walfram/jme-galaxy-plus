package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.order.ProductionOrder;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.domain.production.ProductionContext;
import galaxy.domain.production.ProductionResult;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

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

		for (Entity planet : withProductionOrders) {
			Production production = planet.prop(ProductionOrder.class).production();
			production.advance(tpf);

			if (production.isComplete()) {
				logger.debug("production complete for {}", planet);

				Entity team = galaxy.query(List.of(TeamRef.class, Team.class)).stream()
						.filter(e -> Objects.equals(planet.prop(TeamRef.class), e.prop(TeamRef.class)))
						.findFirst()
						.orElseThrow();

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
