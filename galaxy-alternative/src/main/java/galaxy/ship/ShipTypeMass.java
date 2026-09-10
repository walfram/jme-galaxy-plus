package galaxy.ship;

import galaxy.Scalar;
import galaxy.ShipType;

public final class ShipTypeMass implements Scalar<Double> {
	private final ShipType source;

	public ShipTypeMass(ShipType source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return source.engineSize().mass() + source.weapons().mass() + source.shieldsPower().mass() + source.cargoSize().mass();
	}
}
