package galaxy.core.phase;

import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.Phase;
import galaxy.core.PlanetVisibility;
import galaxy.core.order.FlightOrder;
import galaxy.core.planet.PlanetRef;
import galaxy.core.ship.state.InFlight;
import galaxy.core.ship.state.InOrbit;
import galaxy.core.ship.state.Launched;
import galaxy.core.team.GalaxyView;
import galaxy.core.team.TeamRef;

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
			PlanetRef planetRef = ship.prop(FlightOrder.class).destination();
			ship.remove(FlightOrder.class);

			ship.put(new InOrbit());
			ship.put(planetRef);

			galaxy.team(ship.prop(TeamRef.class)).prop(GalaxyView.class).changeVisibility(planetRef, PlanetVisibility.ORBITING);
		});

		List<Entity> readyToLaunch = galaxy.query(List.of(InOrbit.class, FlightOrder.class));
		readyToLaunch.forEach(ship -> ship.put(new Launched()));
	}
}
