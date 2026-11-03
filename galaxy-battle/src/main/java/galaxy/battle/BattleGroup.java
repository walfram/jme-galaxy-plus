package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;

import java.util.Collection;

public final class BattleGroup {

	private final ShipGroup shipGroup;

	private final Cached<Double> cachedAttack;
	private final Cached<Double> cachedDefense;

	public BattleGroup(ShipGroup shipGroup) {
		this.shipGroup = shipGroup;
		this.cachedAttack = new Cached<>(new NormalizedOffence(shipGroup));
		this.cachedDefense = new Cached<>(new NormalizedDefence(shipGroup));
	}

	public double attackValue() {
		return cachedAttack.get();
	}

	public double defenceValue() {
		return cachedDefense.get();
	}

	@Override
	public String toString() {
		return "BattleGroup(%s, NA=%s, ND=%s".formatted(shipGroup, attackValue(), defenceValue());
	}

	public Race owner() {
		return shipGroup.owner();
	}

	public Collection<BattleGroup> attack(Collection<BattleGroup> targets) {
		return null;
	}

	public boolean attack(BattleGroup target) {
		return false;
	}

	public int guns() {
		return 0;
	}
}
