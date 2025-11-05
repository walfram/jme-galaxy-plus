package galaxy.domain.ship;

import galaxy.domain.technology.EnginesTechnology;

public class Speed {

	private static final double BASE_SPEED = 20.0;

	private final EnginesTechnology engineTech;
	private final double engineWeight;
	private final double shipWeight;
	private final double cargoWeight;

	private final double cargoCapacity;

	public Speed(ShipTemplate shipTemplate) {
		this.engineTech = new EnginesTechnology();
		this.engineWeight = shipTemplate.enginesTemplate().weight();
		this.shipWeight = shipTemplate.weight();
		this.cargoWeight = 0.0;
		this.cargoCapacity = shipTemplate.cargoTemplate().capacity();
	}

	public Speed(Ship ship) {
		this.engineTech = ship.engines().technology();
		this.engineWeight = ship.engines().weight();
		this.shipWeight = ship.weight();
		this.cargoWeight = ship.cargo().loadedQuantity();
		this.cargoCapacity = ship.cargo().cargoCapacity();
	}

	public double value() {
		return BASE_SPEED * engineTech.value() * (engineWeight / (shipWeight + cargoWeight));
	}

	public double fullyLoaded() {
		return BASE_SPEED * engineTech.value() * (engineWeight / (shipWeight + cargoCapacity));
	}
}
