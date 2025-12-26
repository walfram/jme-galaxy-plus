package galaxy.domain.phase;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.shared.BattleGroups;
import galaxy.domain.ship.Weapons;
import galaxy.domain.team.Diplomacy;
import galaxy.domain.team.TeamRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

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
		Map<TeamRef, Entity> teams = galaxy.teams();

		for (PlanetRef planetRef : battleGroups.keySet()) {
			Map<TeamRef, List<Entity>> battleGroup = battleGroups.get(planetRef);
			if (battleGroup.size() <= 1) {
				logger.debug("battle group at {} is empty, skipping", planetRef);
				continue;
			}

			long armedShipCount = battleGroup.values().stream()
					.flatMap(Collection::stream)
					.filter(e -> e.has(Weapons.class))
					.count();
			if (armedShipCount == 0) {
				logger.debug("battle group at {} has no armed ships, skipping", planetRef);
				continue;
			}

			List<TeamRef> teamsInBattleGroup = new ArrayList<>(battleGroup.keySet());
			logger.debug("battle group teams at {}: {}", planetRef, teamsInBattleGroup);

			Map<TeamRef, Set<Entity>> teamTargets = new HashMap<>();

			for (int i = 0; i < teamsInBattleGroup.size(); i++) {
				for (int j = i + 1; j < teamsInBattleGroup.size(); j++) {
					Entity left = teams.get(teamsInBattleGroup.get(i));
					Entity right = teams.get(teamsInBattleGroup.get(j));

					computeTeamTargets(battleGroup, teamTargets, left, right);
					computeTeamTargets(battleGroup, teamTargets, right, left);
				}
			}
			logger.debug("teamTargets at {}: {}", planetRef, teamTargets.size());
			if (teamTargets.isEmpty()) {
				logger.debug("there are no enemies at {}, skipping", planetRef);
				continue;
			}

			List<Entity> armedShips = teamTargets.values().stream()
					.flatMap(Collection::stream)
					.filter(ship -> ship.has(Weapons.class))
					.collect(Collectors.toCollection(ArrayList::new));

			int shotsFired = 0;
			List<Entity> destroyed = new ArrayList<>((int) battleGroup.values().stream().mapToLong(Collection::size).sum());

			while (!armedShips.isEmpty()) {
				// pick armed a ship
				int attackerIdx = generator.nextInt(armedShips.size());
				Entity attacker = armedShips.remove(attackerIdx);

				// derive team
				TeamRef attackingTeam = attacker.prop(TeamRef.class);

				// pick target from list of enemies
				List<Entity> targetShips = teamTargets.get(attackingTeam).stream().toList();
				if (targetShips.isEmpty()) {
					logger.debug("no targets for team {}, skipping", attackingTeam);
					continue;
				}

				int targetIdx = generator.nextInt(targetShips.size());
				Entity target = targetShips.get(targetIdx);
				teamTargets.get(attackingTeam).remove(target);

				// shoot, remove target
				logger.debug("ship {} attacked {} and won", attacker, target);
				armedShips.remove(target);

				shotsFired++;
				destroyed.add(target);
			}

			galaxy.remove(destroyed);

			logger.debug("shots fired at {}: {}, ships destroyed = {}", planetRef, shotsFired, destroyed.size());
		}
	}

	private void computeTeamTargets(Map<TeamRef, List<Entity>> battleGroup, Map<TeamRef, Set<Entity>> teamTargets, Entity left, Entity right) {
		if (left.prop(Diplomacy.class).isAtWarWith(right)) {
			// left targets all right's ships
			List<Entity> allRightShips = battleGroup.values().stream()
					.flatMap(Collection::stream)
					.filter(ship -> Objects.equals(right.prop(TeamRef.class), ship.prop(TeamRef.class)))
					.toList();
			teamTargets
					.computeIfAbsent(left.prop(TeamRef.class), k -> new HashSet<>())
					.addAll(allRightShips);

			// right targets only armed left's ships'
			List<Entity> leftArmedShips = battleGroup.values().stream()
					.flatMap(Collection::stream)
					.filter(ship -> Objects.equals(left.prop(TeamRef.class), ship.prop(TeamRef.class)))
					.filter(ship -> ship.has(Weapons.class))
					.toList();
			teamTargets
					.computeIfAbsent(right.prop(TeamRef.class), k -> new HashSet<>())
					.addAll(leftArmedShips);
		}
	}

//	@Override
//	public void execute(double tpf) {
//		Map<PlanetRef, Map<TeamRef, List<Entity>>> battleGroups = new BattleGroups(galaxy).filter();
//
//		logger.debug("battle groups {}", battleGroups.size());
//		logger.debug("battle groups at planets {}", battleGroups.keySet().stream().map(PlanetRef::value).sorted().toList());
//
//		for (PlanetRef planetRef : battleGroups.keySet()) {
//			Map<TeamRef, List<Entity>> teamShips = battleGroups.get(planetRef);
//			logger.debug("battle group at planet {}: {}", planetRef, teamShips.keySet());
//
//			int shipCount = teamShips.values().stream().mapToInt(Collection::size).sum();
//			List<Entity> discarded = new ArrayList<>(shipCount);
//			List<Entity> destroyed = new ArrayList<>(shipCount);
//
//			int shotsFired = 0;
//			while (
//					teamShips.values().stream()
//							.flatMap(Collection::stream)
//							.anyMatch(e -> e.has(Weapons.class) && !discarded.contains(e))
//			) {
//				List<Entity> allShips = teamShips.values().stream().flatMap(Collection::stream).toList();
//				List<Entity> withWeapons = teamShips.values().stream()
//						.flatMap(Collection::stream)
//						.filter(e -> e.has(Weapons.class))
//						.filter(e -> !discarded.contains(e))
//						.toList();
//
//				Entity attacker = withWeapons.get(generator.nextInt(withWeapons.size()));
//
//				TeamRef attackingTeam = attacker.prop(TeamRef.class);
//				List<Entity> enemyShips = allShips.stream()
//						.filter(e -> !Objects.equals(attackingTeam, e.prop(TeamRef.class)))
//						.filter(e -> attackingTeam.isHostileTo(e.prop(TeamRef.class)))
//						.toList();
//
//				Entity target = enemyShips.get(generator.nextInt(enemyShips.size()));
//
//				logger.debug("ship {} attacked {} and won", attacker, target);
//
//				discarded.add(attacker);
//				battleGroups.get(planetRef).get(target.prop(TeamRef.class)).remove(target);
//				destroyed.add(target);
//				shotsFired++;
//			}
//
//			// TODO increase planet's materials by weight of destroyed ships
//			// TODO mark as Destroyed? use tpf for battle?
//			galaxy.remove(destroyed);
//
//			logger.debug("shots fired at {}: {}", planetRef, shotsFired);
//		}
//
//	}
}
