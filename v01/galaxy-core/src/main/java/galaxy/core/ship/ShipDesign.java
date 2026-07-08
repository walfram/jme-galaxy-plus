package galaxy.core.ship;

import galaxy.core.Component;

public record ShipDesign(double engines, int guns, double caliber, double shields, double cargo) implements Component {
	public double enginesWeight() {
		return engines;
	}

	public double weaponsWeight() {
		if (guns == 1) {
			return caliber;
		} else if (guns > 1) {
			return caliber + ((guns - 1) * 0.5 * caliber);
		} else {
			return 0.0;
		}
	}

	public double shieldsWeight() {
		return shields;
	}

	public double cargoWeight() {
		return cargo;
	}

	public double weight() {
		return enginesWeight() + weaponsWeight() + shieldsWeight() + cargoWeight();
	}

	public double cargoCapacity() {
		return ( cargoWeight() + (cargoWeight() * cargoWeight()) / 20.0 );
	}
}
