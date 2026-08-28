package galaxy.production;

import galaxy.*;
import galaxy.planet.Planet;

public class TechResearchProduction implements Production {
	private final Race race;
	private final Planet planet;
	private final Technology technology;

	public TechResearchProduction(Race race, Planet planet, Technology technology) {
		this.race = race;
		this.planet = planet;
		this.technology = technology;
	}

	@Override
	public void update(GameContext context) {
		double effort = planet.effort();

		double delta = effort / 5000.0;

		race.techLevels().update(technology, delta);
	}
}
