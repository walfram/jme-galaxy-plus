package galaxy.ship;

import galaxy.Scalar;
import galaxy.ShipGroup;

public class MassOf implements Scalar<Double> {
	private final ShipGroup source;

	public MassOf(ShipGroup source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return source.shipType().mass() + cargoMass();
	}

	private double cargoMass() {
		if (source.shipType().cargoSize().isPresent()) {
			return source.cargo().mass();
		}

		return 0;
	}
}
