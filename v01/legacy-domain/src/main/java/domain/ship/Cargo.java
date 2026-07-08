package domain.ship;

import domain.technology.CargoTechnology;

public class Cargo {
	private final CargoTemplate template;
	private final CargoTechnology technology;

	private double loadedQuantity;

	public Cargo(CargoTemplate template, CargoTechnology technology) {
		this.template = template;
		this.technology = new CargoTechnology(technology);
	}

	public double techLevel() {
		return technology.value();
	}

	public double weight() {
		return template.weight();
	}

	// TODO must not exceed effective cargo weight
	public double loadedQuantity() {
		return loadedQuantity;
	}

	public double cargoCapacity() {
		return new CargoCapacity(this).value();
	}

}
