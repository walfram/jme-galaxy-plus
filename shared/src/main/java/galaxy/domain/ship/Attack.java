package galaxy.domain.ship;

public record Attack(Ship ship) {

	public double value() {
		return ship.weapons().caliber() * ship.weapons().techLevel();
	}

}
