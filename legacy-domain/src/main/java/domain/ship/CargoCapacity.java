package domain.ship;

import domain.technology.CargoTechnology;

public class CargoCapacity {

	private final double weight;
	private final CargoTechnology technology;

	public CargoCapacity(Cargo cargo) {
		this(cargo.weight(), new CargoTechnology());
	}

	public CargoCapacity(double weight, CargoTechnology technology) {
		this.weight = weight;
		this.technology = technology;
	}

	public CargoCapacity(CargoTemplate template) {
		this(template.weight(), new CargoTechnology());
	}

	public double value() {
		return technology.value() * ( weight + (weight * weight) / 20.0 );
	}
}
