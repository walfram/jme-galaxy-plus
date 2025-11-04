package galaxy.domain.ship;

import galaxy.domain.technology.WeaponsTechnology;

public class Weapons {
	private final WeaponsTemplate template;
	private final WeaponsTechnology technology;

	public Weapons(WeaponsTemplate template, WeaponsTechnology technology) {
		this.template = template;
		this.technology = technology;
	}

	public double weight() {
		return template.weight();
	}
}
