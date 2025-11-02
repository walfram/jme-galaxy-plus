package galaxy.domain.ship;

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
}
