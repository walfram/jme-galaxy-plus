package galaxy.domain.ship;

import galaxy.domain.technology.ShieldsTechnology;

public class Shields {
	private final ShieldsTemplate template;
	private final ShieldsTechnology technology;

	public Shields(ShieldsTemplate template, ShieldsTechnology technology) {
		this.template = template;
		this.technology = technology;
	}

	public double weight() {
		return template.weight();
	}
}
