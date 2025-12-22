package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.order.FlightOrder;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.ShipState;
import galaxy.domain.team.TeamRef;

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
			// TODO update position
			// check if at destination
			PlanetRef planetRef = ship.prop(FlightOrder.class).to();
			ship.remove(FlightOrder.class);

			ship.put(ShipState.InOrbit);
			ship.put(planetRef);

			TeamGalaxyView teamGalaxyView = galaxy.galaxyView(ship.prop(TeamRef.class));
			teamGalaxyView.updateVisibility(planetRef, PlanetVisibility.ORBITING);
		});

		// Given planet and ships - visibility cannot change to "visited" until all ships departed

		List<Entity> readyToLaunch = galaxy.query(List.of(ShipState.InOrbit.getClass(), FlightOrder.class));
		readyToLaunch.forEach(ship -> {
			ship.put(ShipState.Launched);
		});
	}
}
