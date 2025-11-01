package galaxy.domain.production;

import galaxy.domain.Planet;
import galaxy.domain.technology.Technology;

public class TechnologyProduction extends Production {

	private final Technology technology;

	public TechnologyProduction(Planet planet, Technology technology) {
		super(planet);
		this.technology = technology;
	}

	@Override
	public void execute() {
		technology.upgrade(planet.effort());
	}

}
