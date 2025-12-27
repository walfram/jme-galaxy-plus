package galaxy.core.derived;

import galaxy.core.ship.ShipDesign;
import galaxy.core.ship.TechLevel;

import static galaxy.core.Const.BASE_SPEED;

public class Speed {
	private final ShipDesign design;
	private final TechLevel techLevel;

	public Speed(ShipDesign design, TechLevel techLevel) {
		this.design = design;
		this.techLevel = techLevel;
	}

	public double value() {
		return BASE_SPEED * techLevel.engines() * (design.enginesWeight() / (design.weight()));
	}

	public double valueLoaded() {
		return BASE_SPEED * techLevel.engines() * (design.enginesWeight() / (design.weight() + design.cargoCapacity()));
	}
}
