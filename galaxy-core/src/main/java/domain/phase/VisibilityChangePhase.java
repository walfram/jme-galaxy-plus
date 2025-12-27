package domain.phase;

import domain.Context;
import domain.Entity;
import domain.Phase;
import domain.PlanetVisibility;
import domain.planet.PlanetRef;
import domain.ship.state.InOrbit;
import domain.team.GalaxyView;
import domain.team.TeamRef;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class VisibilityChangePhase implements Phase {
	private final Context galaxy;

	public VisibilityChangePhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		Collection<Entity> teams = galaxy.teams().values();
		Collection<Entity> planets = galaxy.planets().values();
		Collection<Entity> ships = galaxy.ships().values();

		for(Entity team : teams) {
			List<Entity> teamShips = ships.stream().filter(ship -> Objects.equals(ship.prop(TeamRef.class), team.prop(TeamRef.class))).toList();

			for (Entity planet: planets) {
				boolean hasOrbitingShips = teamShips.stream()
						.filter(ship -> Objects.equals(ship.prop(PlanetRef.class), planet.prop(PlanetRef.class)))
						.anyMatch(ship -> ship.has(InOrbit.class));

				if (Objects.equals(team.prop(TeamRef.class),  planet.prop(TeamRef.class))) {
					team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.OWNED);
				} else if (hasOrbitingShips) {
					team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.ORBITING);
				} else {
					PlanetVisibility visibility = team.prop(GalaxyView.class).visibility(planet);
					if (visibility == PlanetVisibility.ORBITING) {
						// but no ships at this point - either all left planet or destroyed
						team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.VISITED);
					}
				}
			}
		}
	}

}
