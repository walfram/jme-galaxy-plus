package galaxy.core.derived;

import galaxy.core.ship.ShipDesign;
import galaxy.core.TechLevels;

import static galaxy.core.Const.BASE_SPEED;

public class Speed {
	private final ShipDesign design;
	private final TechLevels techLevels;

	public Speed(ShipDesign design, TechLevels techLevels) {
		this.design = design;
		this.techLevels = techLevels;
	}

	public double value() {
		return BASE_SPEED * techLevels.engines() * (design.enginesWeight() / (design.weight()));
	}

	public double valueLoaded() {
		return BASE_SPEED * techLevels.engines() * (design.enginesWeight() / (design.weight() + design.cargoCapacity()));
	}
}
