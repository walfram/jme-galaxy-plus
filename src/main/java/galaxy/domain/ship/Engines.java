package galaxy.domain.ship;

import galaxy.domain.technology.EnginesTechnology;

public record Engines(EnginesTemplate template, EnginesTechnology technology) {

	public double value() {
		return template.value();
	}

	public double techLevel() {
		return technology.value();
	}

	public double weight() {
		return template.weight();
	}
}
