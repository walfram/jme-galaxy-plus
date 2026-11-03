package galaxy.battle;

import galaxy.Race;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class BattleCheck {

	private final Attackers attackers;

//	@SafeVarargs
//	public BattleCheck(Collection<BattleGroup>... groups) {
//		this.attackers = new Attackers(groups);
//	}

	public BattleCheck(Map<Race, Collection<BattleGroup>> fleets) {
		this.attackers = new Attackers(fleets);
	}

	public boolean canFight() {
		boolean hasEnemies = true;

		boolean hasAttackers = !attackers.asList().isEmpty();

		// TODO check attacker maxOffence + 1.0 is greater then minDefence

		return hasEnemies && hasAttackers;
	}
}
