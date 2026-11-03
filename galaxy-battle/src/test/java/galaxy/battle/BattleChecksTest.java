package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleChecksTest {

	private final Race red = new Race("Red");
	private final Race green = new Race("Green");

	private final Map<Race, Collection<ShipGroup>> fleets = Map.of(
			red, new RandomSystemFleet(red).generateFleet(),
			green, new RandomSystemFleet(green).generateFleet()
	);

	@Test
	void test_max_offence_vs_min_defence() {
		List<Race> races = new ArrayList<>(fleets.keySet());

		for (Race attacker : races) {
			List<Race> others = new ArrayList<>(races);
			others.remove(attacker);

			for (Race defender : others) {
				Collection<BattleGroup> attackers = new BattleGroupsOf(fleets.get(attacker)).value();
				BattleGroup bestAttacker = attackers.stream().max(Comparator.comparing(BattleGroup::attackValue)).orElseThrow();

				Collection<BattleGroup> defenders = new BattleGroupsOf(fleets.get(defender)).value();
				BattleGroup worstDefender = defenders.stream().min(Comparator.comparing(BattleGroup::defenceValue)).orElseThrow();

				assertTrue(bestAttacker.attackValue() + 1.0 >= worstDefender.defenceValue());
			}
		}
	}

}
