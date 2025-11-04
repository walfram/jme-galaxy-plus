package galaxy.domain.ship;

import galaxy.domain.technology.EnginesTechnology;

public class Engines {

	private final EnginesTemplate template;
	private final EnginesTechnology technology;

	public Engines(EnginesTemplate enginesTemplate, EnginesTechnology technology) {
		this.template = new EnginesTemplate(enginesTemplate);
		this.technology = technology;
	}

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
