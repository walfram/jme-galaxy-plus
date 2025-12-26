package galaxy.domain.shared;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.ShipId;
import galaxy.domain.ship.state.InOrbit;
import galaxy.domain.team.TeamRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleGroups {
	private final Context galaxy;

	public BattleGroups(Context galaxy) {
		this.galaxy = galaxy;
	}

	public Map<PlanetRef, Map<TeamRef, List<Entity>>> filter() {
		List<Entity> ships = galaxy.query(List.of(ShipId.class, InOrbit.class));

		Map<PlanetRef, Map<TeamRef, List<Entity>>> battleGroups = new HashMap<>();

		for (Entity ship : ships) {
			PlanetRef planetRef = ship.prop(PlanetRef.class);
			TeamRef teamRef = ship.prop(TeamRef.class);

			battleGroups
					.computeIfAbsent(planetRef, p -> new HashMap<>())
					.computeIfAbsent(teamRef, t -> new ArrayList<>())
					.add(ship);
		}

		return battleGroups;
	}
}
