package galaxy.battle;

import java.util.List;
import java.util.random.RandomGenerator;

public final class RandomAttacker {

	private final List<BattleGroup> attackers;
	private final RandomGenerator random;

	public RandomAttacker(List<BattleGroup> attackers, RandomGenerator random) {
		this.attackers = attackers;
		this.random = random;
	}

	public BattleGroup value() {
		int idx = random.nextInt(attackers.size());
		return attackers.get(idx);
	}
}
