package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public final class BattleGenerator {

	private static final Logger logger = getLogger(BattleGenerator.class);

	private final Map<Race, Collection<ShipGroup>> fleets;
	private final Generator generator;

	public BattleGenerator(Map<Race, Collection<ShipGroup>> fleets) {
		this.fleets = fleets;
		this.generator = new Generator(1337);
	}

	public Map<Race, Collection<ShipGroup>> generate() {
		logger.info("Generating battle");

		Map<Race, Collection<BattleGroup>> battleGroups = new HashMap<>(fleets.size());

		for (Map.Entry<Race, Collection<ShipGroup>> entry : fleets.entrySet()) {
			Race race = entry.getKey();
			Collection<ShipGroup> ships = entry.getValue();
			battleGroups.put(race, new BattleGroupsOf(ships).value());
		}

		logger.info("battle groups size = {}", battleGroups.size());

		List<BattleGroup> attackers = new Attackers(battleGroups).asList();
		if (attackers.isEmpty()) {
			logger.info("No armed ship groups found, no battle commenced");
			return Map.copyOf(fleets);
		}

		// TODO check races >= 2 ???

		Optional<BattleGroup> checkAttacker = new MaxOffence(battleGroups).shipGroup();
		if (checkAttacker.isEmpty()) {
			logger.info("No check attacker found, no battle commenced");
			return Map.copyOf(fleets);
		}

		Optional<BattleGroup> checkDefender = new MinDefence(battleGroups).shipGroup();
		if (checkDefender.isEmpty()) {
			logger.info("No check defender found, no battle commenced");
			return Map.copyOf(fleets);
		}

		while (true) {
			BattleGroup attacker = new RandomAttacker(attackers, generator).pick();

			Collection<BattleGroup> targets = new Targets(battleGroups, attacker).pick();

			int shots = Math.min(attacker.guns(), targets.size());

			for (int shot = 0; shot < shots; shot++) {
				long skip = generator.nextLong(targets.size());
				BattleGroup target = targets.stream().skip(skip).findFirst().orElseThrow();
				boolean attack = attacker.attack(target);

				if (attack) {
					logger.info("DESTROY: {} attacked {}", attacker, target);
					battleGroups.get(target.owner()).remove(target);
				} else {
					logger.info("MISS: {} attacked {}", attacker, target);
				}
			}

			Attackers check = new Attackers(battleGroups);
			if (check.asList().isEmpty()) {
				logger.info("No attackers left, battle finished");
				break;
			}
		}

		return Map.copyOf(fleets);
	}
}
