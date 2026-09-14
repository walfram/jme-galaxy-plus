package galaxy.ship;

import galaxy.Scalar;

public class WeaponsMass implements Scalar<Double> {
	private final Weapons source;

	public WeaponsMass(Weapons source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return source.caliber() * (source.guns() + 1) / 2.0;
	}
}
