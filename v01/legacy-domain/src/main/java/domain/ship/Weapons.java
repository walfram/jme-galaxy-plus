package domain.ship;

import domain.technology.WeaponsTechnology;

public record Weapons(WeaponsTemplate template, WeaponsTechnology technology) {

	public Weapons {
		technology = new WeaponsTechnology(technology);
	}

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
