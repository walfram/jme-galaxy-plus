package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;
import galaxy.order.Orders;
import galaxy.planet.RandomRaceSelection;
import jme3utilities.math.noise.Generator;

public final class StandardTurn implements Phase {
	private final Phase origin;

	public StandardTurn() {
		this.origin = new Turn(
//				new AdministrativePhase(),   // ChangeDiplomacy, DefineShipType, DefineScience,
				// NamePlanet, SplitShipGroup, BreakShipGroup, GiftShipGroup
				new QuitBySleep(),
				new QuitByNoPlanets(),
				new ShipTransfer(),
				new JoinShipGroups(),
				new CombatPhase(),
				new LoadCargo(),
				new SendShipGroupsPhase(),
				new MovePhase(),
				new JoinShipGroups(),
				new CombatPhase(),
				new BombingPhase(),
				new ProductionPhase(),
				new UnloadPhase(new RandomRaceSelection(new Generator())),
				new JoinShipGroups(),
				new VictoryCheck()
		);
	}

	@Override
	public GameContext process(final GameContext context, final Orders orders) {
		return this.origin.process(context, orders);
	}
}
