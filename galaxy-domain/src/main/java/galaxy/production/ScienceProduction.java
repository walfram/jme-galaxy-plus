package galaxy.production;

import galaxy.*;
import galaxy.planet.Planet;

public class ScienceProduction implements Production {
	private final Race race;
	private final Planet planet;
	private final Science science;

	public ScienceProduction(Race race, Planet planet, Science science) {
		this.race = race;
		this.planet = planet;
		this.science = science;
	}

	@Override
	public void update(GameContext context) {
		double effort = planet.effort();
		double e = effort / 5000.0;

		double deltaEngines = e * science.engines();
		race.techLevels().updateEngines(deltaEngines);

		double deltaWeapons = e * science.weapons();
		race.techLevels().updateWeapons(deltaWeapons);

		double deltaShields = e * science.shields();
		race.techLevels().updateShields(deltaShields);

		double deltaCargo = e * science.cargo();
		race.techLevels().updateCargo(deltaCargo);
	}
}
