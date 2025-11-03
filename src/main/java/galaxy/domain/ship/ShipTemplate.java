package galaxy.domain.ship;

import galaxy.domain.Ship;
import galaxy.domain.technology.Technology;

import java.util.List;

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

	public Ship build(List<Technology> technologies) {
		return new Ship(
				new Engines(enginesTemplate),
				new Weapons(weaponsTemplate),
				new Shields(shieldsTemplate),
				new Cargo(cargoTemplate)
		);
	}
}
