package galaxy.production;

import galaxy.*;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

// TODO this upgrades all ship group techs - must be option to upgrade single/multiple techs
public class ShipGroupUpgradeProduction implements Production {

	private final Race race;
	private final Planet planet;
	private final ShipGroup shipGroup;

	public ShipGroupUpgradeProduction(Race race, Planet planet, ShipGroup shipGroup) {
		this.race = race;
		this.planet = planet;
		this.shipGroup = shipGroup;
	}

	public ShipGroup shipGroup() {
		return shipGroup;
	}

	@Override
	public void update(GameContext context) {
		TechLevels shipTechLevels = shipGroup.techLevels();
		TechLevels raceTechLevels = race.techLevels();
		ShipType shipType = shipGroup.shipType();

		double enginesCost = 10.0 * (1.0 - shipTechLevels.engines() / raceTechLevels.engines()) * shipType.engines().mass();

		double w = (shipType.weapons().guns() * shipType.weapons().caliber() + shipType.weapons().caliber()) / 2.0;
		double weaponsCost = 10.0 * (1.0 - shipTechLevels.weapons() / raceTechLevels.weapons()) * w;

		double shieldsCost = 10.0 * (1.0 - shipTechLevels.shields() / raceTechLevels.engines()) * shipType.shields().mass();

		double cargoCost = 10.0 * (1.0 - shipTechLevels.cargo() / raceTechLevels.cargo()) * shipType.cargoHold().mass();

		double requiredEffort = shipGroup.size() * (enginesCost + weaponsCost + shieldsCost + cargoCost);
		double effort = planet.effort();

		double ratio = effort / requiredEffort;

		// TODO ratio, required effort check!!!

		if (effort >= requiredEffort) {
			shipTechLevels.updateFrom(raceTechLevels, 1.0);
		} else {
			shipTechLevels.updateFrom(raceTechLevels, ratio);
		}
	}

}
