package galaxy.domain.ship;

import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.technology.Technologies;

public record ShipTemplate(
		String name,
		EnginesTemplate enginesTemplate,
		WeaponsTemplate weaponsTemplate,
		ShieldsTemplate shieldsTemplate,
		CargoTemplate cargoTemplate
) {

	public double weight() {
		return enginesTemplate.weight() + weaponsTemplate.weight() + shieldsTemplate.weight() + cargoTemplate.weight();
	}

	public Ship build(Technologies technologies, Race owner, Planet location) {
		return new Ship(
				new Engines(enginesTemplate, technologies.engines()),
				new Weapons(weaponsTemplate, technologies.weapons()),
				new Shields(shieldsTemplate, technologies.shields()),
				new Cargo(cargoTemplate, technologies.cargo()),
				owner, location
		);
	}

	public double speed() {
		return new Speed(this).value();
	}

	public double speedLoaded() {
		return new Speed(this).fullyLoaded();
	}
}
