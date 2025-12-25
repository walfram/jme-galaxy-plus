package galaxy.domain.phase;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.planet.Industry;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.planet.Population;
import galaxy.domain.shared.BattleGroups;
import galaxy.domain.shared.CalculatedBombing;
import galaxy.domain.team.TeamRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BombingPhase implements Phase {

	private static final Logger logger = LoggerFactory.getLogger(BombingPhase.class);

	private final Context galaxy;

	public BombingPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		// find battle groups
		Map<PlanetRef, Map<TeamRef, List<Entity>>> battleGroups = new BattleGroups(galaxy).filter();

		Map<PlanetRef, Entity> planetCache = galaxy.planets();

		// bomb!
		for (PlanetRef planetRef : battleGroups.keySet()) {
			Entity targetPlanet = planetCache.get(planetRef);

			if (!targetPlanet.has(Population.class)) {
				logger.debug("cannot bomb planet {} - no population", planetRef);
				continue;
			}

			if (!targetPlanet.has(Industry.class)) {
				logger.debug("cannot bomb planet {} - no industry", planetRef);
				continue;
			}

			Map<TeamRef, List<Entity>> shipGroups = battleGroups.get(planetRef);

			TeamRef teamRef = targetPlanet.prop(TeamRef.class);
			List<TeamRef> attackers = shipGroups.keySet().stream()
					// exclude self - should not be the case, 'cause bombing occures after space fight
					// and defenders should be eliminated by now
					.filter(team -> !Objects.equals(team, teamRef))
					.filter(team -> team.isHostileTo(teamRef))
					.toList();

			for (TeamRef attacker : attackers) {
				List<Entity> attackingShips = shipGroups.get(attacker);
				double bombing = new CalculatedBombing(attackingShips).value();
				Population population = targetPlanet.prop(Population.class);

				if ((population.value() - bombing) <= 0) {
					targetPlanet.remove(Population.class);
					targetPlanet.remove(TeamRef.class);
					targetPlanet.remove(Industry.class);
					logger.debug("team {} wiped planet {}: {}", attacker, planetRef, targetPlanet);
					break;
				} else {
					Industry industry = targetPlanet.prop(Industry.class);
					targetPlanet.put(new Population(population.value() - bombing));
					targetPlanet.put(new Industry(industry.value() - bombing));
					logger.debug("team {} dealt bombing damage {} to {}: {}", attacker, bombing, planetRef, targetPlanet);
				}
			}

		}
	}
}
