package galaxy.domain.ship;

import galaxy.domain.technology.CargoTechnology;

public class Cargo {
	private final CargoTemplate template;
	private final CargoTechnology technology;

	private double loadedQuantity;

	public Cargo(CargoTemplate template, CargoTechnology technology) {
		this.template = template;
		this.technology = technology;
	}

	public double techLevel() {
		return technology.value();
	}

	public double weight() {
		return template.weight();
	}

	public double loadedQuantity() {
		return loadedQuantity;
	}
}
