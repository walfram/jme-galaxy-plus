package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;
import galaxy.decorators.GameContextOf;
import galaxy.order.Orders;
import galaxy.planet.RaceSelection;

public final class UnloadPhase implements Phase {
	private final RaceSelection selection;

	public UnloadPhase(final RaceSelection selection) {
		this.selection = selection;
	}

	@Override
	public GameContext process(GameContext context, Orders orders) {
		return new GameContextOf(context);
	}

//	@Override
//	public GameContext process(final GameContext context, final Orders orders) {
//		Planets planets = context.planets();
//		for (final Planet planet : context.planets().all()) {
//			Planet unloaded = this.unloaded(context, orders, planet);
//			planets = new ReplacedPlanets(planets, unloaded);
//		}
//
//		final ShipGroups groups = context.shipGroups().all().stream()
//				.map(g -> this.wasOrderedToUnload(orders, g) ? g.unloaded() : g)
//				.collect(Collectors.collectingAndThen(Collectors.toList(), ShipGroupsOf::new));
//
//		return new WithShipGroups(new WithPlanets(context, planets), groups);
//	}
//
//	private boolean wasOrderedToUnload(final Orders orders, final ShipGroup group) {
//		return orders.unloadShipGroups().stream()
//				.anyMatch(o -> o.groupNumber().equals(group.id()));
//	}
//
//	private Planet unloaded(final GameContext context, final Orders orders, final Planet planet) {
//		final List<ShipGroup> groups = orders.unloadShipGroups().stream()
//				.filter(o -> o.planetName().equals(planet.name()))
//				.map(o -> context.shipGroups().byGroupId(o.groupNumber()))
//				.toList();
//
//		Planet result = planet;
//		result = new Colonization(
//				result,
//				groups.stream().filter(g -> g.cargoType() == CargoType.COLONISTS).collect(Collectors.toList()),
//				this.selection
//		).outcome();
//
//		result = groups.stream()
//				.filter(g -> g.cargoType() == CargoType.MATERIAL)
//				.reduce(result, (p, g) -> new MaterialUnloading(p, g).outcome(), (a, b) -> b);
//
//		result = groups.stream()
//				.filter(g -> g.cargoType() == CargoType.CAPITAL)
//				.reduce(result, (p, g) -> new CapitalUnloading(p, g).outcome(), (a, b) -> b);
//
//		return result;
//	}
}
