package galaxy.domain.ship;

import galaxy.domain.technology.CargoTechnology;

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

	// TODO must no exceed effective cargo weight
	public double loadedQuantity() {
		return loadedQuantity;
	}

	// TODO weight -> capacity
	public double effectiveCargoWeight() {
		double cargoSize = template.weight();
		return technology.value() * ( cargoSize + (cargoSize * cargoSize) / 20 );
	}
}
