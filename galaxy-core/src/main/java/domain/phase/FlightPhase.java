package domain.phase;

import domain.*;
import domain.order.FlightOrder;
import domain.planet.PlanetRef;
import domain.ship.state.InFlight;
import domain.ship.state.InOrbit;
import domain.ship.state.Launched;
import domain.team.GalaxyView;
import domain.team.TeamRef;

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
