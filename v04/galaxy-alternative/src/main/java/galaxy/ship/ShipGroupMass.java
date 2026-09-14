package galaxy.ship;

import galaxy.Scalar;
import galaxy.ShipGroup;

public class ShipGroupMass implements Scalar<Double> {
	private final ShipGroup source;

	public ShipGroupMass(ShipGroup source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return new ShipTypeMass(source.shipType()).value() + cargoMass();
	}

	private double cargoMass() {
		if (source.shipType().cargoSize().isPresent()) {
			return source.cargo().mass();
		}

		return 0;
	}
}
