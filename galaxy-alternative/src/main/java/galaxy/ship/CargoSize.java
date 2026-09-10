package galaxy.ship;

import galaxy.Scalar;

public interface CargoSize extends Scalar<Double> {
	boolean isPresent();

	Double mass();

	Cargo toCargo();
}
