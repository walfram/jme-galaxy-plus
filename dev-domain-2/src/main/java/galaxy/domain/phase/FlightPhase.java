package galaxy.domain.phase;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.order.FlightOrder;
import galaxy.domain.ship.ShipState;

import java.util.List;

public class FlightPhase implements Phase {
	private final Context galaxy;

	public FlightPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> launched = galaxy.query(List.of(ShipState.Launched.getClass(), FlightOrder.class));
		launched.forEach(ship -> {
			ship.put(ShipState.InFlight);
		});

		List<Entity> inFlight = galaxy.query(List.of(ShipState.InFlight.getClass(), FlightOrder.class));
		inFlight.forEach(ship -> {
			// update position
			// check if at destination
		});

		List<Entity> readyToLaunch = galaxy.query(List.of(ShipState.InOrbit.getClass(), FlightOrder.class));
		readyToLaunch.forEach(ship -> {
			ship.put(ShipState.Launched);
		});
	}
}
