package galaxy.domain.ship;

public record Bombing(Ship ship) {

	// Вооруженность == guns
	// Оружие == caliber
	// ( ( ( Оружие * Тех.Ур.Оружия )^(1/2) ) / 10 + 1 ) * Оружие * Тех.Ур.Оружия * Вооруженность

	public double value() {
		double caliber = ship.weapons().caliber();
		double guns = ship.weapons().guns();

		double weaponsLevel = ship.weapons().techLevel();

		double n = 1 + 0.1 * Math.pow(caliber * weaponsLevel, 0.5);

		return n * caliber * weaponsLevel * guns;
	}

}
