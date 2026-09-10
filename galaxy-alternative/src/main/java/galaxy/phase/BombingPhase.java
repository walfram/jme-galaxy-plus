package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;
import galaxy.Planet;
import galaxy.Planets;
import galaxy.context.WithPlanets;
import galaxy.decorators.ReplacedPlanets;
import galaxy.order.Orders;
import galaxy.planet.Bombing;

public final class BombingPhase implements Phase {
	@Override
	public GameContext process(GameContext context, Orders orders) {
		return new WithPlanets(context, this.bombed(context));
	}

	private Planets bombed(final GameContext context) {
//		Planets planets = context.planets();
//		for (final Planet planet : context.planets().all()) {
//			final Planet outcome = context.shipGroups().all().stream().reduce(
//					planet,
//					(p, group) -> new Bombing(p, group).outcome(),
//					(a, b) -> b
//			);
//			planets = new ReplacedPlanets(planets, outcome);
//		}
//		return planets;

		return context.planets();
	}

}
