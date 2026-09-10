package galaxy.ship;

import galaxy.Scalar;
import galaxy.ShipType;

public class ShipTypeSpeed implements Scalar<Double> {
	private final ShipType source;

	public ShipTypeSpeed(ShipType source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return 20.0 * (source.engineSize().mass() / new ShipTypeMass(source).value());
	}
}
