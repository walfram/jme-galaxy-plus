package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.shared.BattleGroups;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.Weapons;
import galaxy.domain.team.TeamRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.random.RandomGenerator;

public class CombatPhase implements Phase {

	private static final Logger logger = LoggerFactory.getLogger(CombatPhase.class);

	private final RandomGenerator generator = RandomGenerator.getDefault();

	private final Context galaxy;

	public CombatPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		Map<PlanetRef, Map<TeamRef, List<Entity>>> battleGroups = new BattleGroups(galaxy).filter();

		logger.debug("battle groups {}", battleGroups.size());
		logger.debug("battle groups at planets {}", battleGroups.keySet().stream().map(PlanetRef::value).sorted().toList());

		for (PlanetRef planetRef : battleGroups.keySet()) {
			Map<TeamRef, List<Entity>> teamShips = battleGroups.get(planetRef);
			logger.debug("battle group at planet {}: {}", planetRef, teamShips.keySet());

			int shipCount = teamShips.values().stream().mapToInt(Collection::size).sum();
			List<Entity> discarded = new ArrayList<>(shipCount);
			List<Entity> destroyed = new ArrayList<>(shipCount);

			int shotsFired = 0;
			while (
					teamShips.values().stream()
							.flatMap(Collection::stream)
							.anyMatch(e -> e.has(Weapons.class) && !discarded.contains(e))
			) {
				List<Entity> allShips = teamShips.values().stream().flatMap(Collection::stream).toList();
				List<Entity> withWeapons = teamShips.values().stream()
						.flatMap(Collection::stream)
						.filter(e -> e.has(Weapons.class))
						.filter(e -> !discarded.contains(e))
						.toList();

				Entity attacker = withWeapons.get(generator.nextInt(withWeapons.size()));

				TeamRef attackingTeam = attacker.prop(TeamRef.class);
				List<Entity> enemyShips = allShips.stream()
						.filter(e -> !Objects.equals(attackingTeam, e.prop(TeamRef.class)))
						.filter(e -> attackingTeam.isHostileTo(e.prop(TeamRef.class)))
						.toList();

				Entity target = enemyShips.get(generator.nextInt(enemyShips.size()));

				logger.debug("ship {} attacked {} and won", attacker, target);

				discarded.add(attacker);
				battleGroups.get(planetRef).get(target.prop(TeamRef.class)).remove(target);
				destroyed.add(target);
				shotsFired++;
			}

			// TODO increase planet's materials by weight of destroyed ships
			// TODO mark as Destroyed? use tpf for battle?
			galaxy.remove(destroyed);

			logger.debug("shots fired at {}: {}", planetRef, shotsFired);
		}

	}
}
