package galaxy.domain.ship;

public record Defence(Ship ship) {

	public double value() {
		double weight = ship.weight();
		double cargo = ship.cargo().loadedQuantity();

		double n = ship.shields().weight() * ship.shields().techLevel();
		double d = Math.pow( weight + cargo, 1.0 / 3.0);

		return (n / d) * Math.pow(30.0, 1.0 / 3.0);
	}

}
