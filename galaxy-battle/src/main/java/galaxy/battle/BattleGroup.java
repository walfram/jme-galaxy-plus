package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipGroupId;
import jme3utilities.math.noise.Generator;

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

	public ShipGroupId shipGroupId() {
		return shipGroup.shipGroupId();
	}

	public Race owner() {
		return shipGroup.owner();
	}

	public boolean attack(BattleGroup target, Generator random) {
		double excess = attackValue() - target.defenceValue();

		if( excess <= -1.0 )
			// return Shot.Result.ALWAYSSHIELDS;
			return false;

		if( excess >= 1.0 )
			// return Shot.Result.DESTROYED;
			return true;

		if( excess > 2.0 * random.nextDouble() - 1.0 )
			// return Shot.Result.DESTROYED;
			return true;

		// return Shot.Result.SHIELDS;
		return false;
	}

	public int guns() {
		return shipGroup.type().weapons().guns();
	}

	public ShipGroup asShipGroup(long count) {
		return new ShipGroup(shipGroup, count);
	}
}
