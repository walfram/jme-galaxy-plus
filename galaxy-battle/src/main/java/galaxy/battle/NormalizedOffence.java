package galaxy.battle;

import galaxy.ship.ShipGroup;

import java.util.function.Supplier;

public record NormalizedOffence(ShipGroup shipGroup) implements Supplier<Double> {

	private static final double LOG_BASE = Math.log(4.0);

	public double value() {
		return Math.log(shipGroup.attackValue()) / LOG_BASE;
	}

	@Override
	public Double get() {
		return value();
	}
}
