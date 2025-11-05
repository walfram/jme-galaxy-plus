package galaxy.domain.ship;

import galaxy.domain.technology.WeaponsTechnology;

public record Weapons(WeaponsTemplate template, WeaponsTechnology technology) {

	public double weight() {
		return template.weight();
	}

	public double techLevel() {
		return technology.value();
	}

	public double caliber() {
		return template.caliber();
	}

	public double guns() {
		return template.guns();
	}

}
