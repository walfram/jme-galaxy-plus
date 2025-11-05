package galaxy.domain.ship;

import galaxy.domain.technology.ShieldsTechnology;

public record Shields(ShieldsTemplate template, ShieldsTechnology technology) {

	public Shields {
		technology = new ShieldsTechnology(technology);
	}

	public double weight() {
		return template.weight();
	}

	public double techLevel() {
		return technology.value();
	}
}
