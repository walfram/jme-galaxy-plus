package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.order.FlightOrder;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.state.InFlight;
import galaxy.domain.ship.state.InOrbit;
import galaxy.domain.ship.state.Launched;
import galaxy.domain.team.GalaxyView;
import galaxy.domain.team.TeamRef;

import java.util.List;

public class FlightPhase implements Phase {
	private final Context galaxy;

	public FlightPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> launched = galaxy.query(List.of(Launched.class, FlightOrder.class));
		launched.forEach(ship -> {
			ship.put(new InFlight());
		});

		List<Entity> inFlight = galaxy.query(List.of(InFlight.class, FlightOrder.class));
		inFlight.forEach(ship -> {
			// TODO update position
			// check if at destination
			PlanetRef planetRef = ship.prop(FlightOrder.class).to();
			ship.remove(FlightOrder.class);

			ship.put(new InOrbit());
			ship.put(planetRef);
		});

		List<Entity> readyToLaunch = galaxy.query(List.of(InOrbit.class, FlightOrder.class));
		readyToLaunch.forEach(ship -> ship.put(new Launched()));
	}
}
