package galaxy.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

public final class Upgrade {
	private final ShipGroup ship;
	private final Planet planet;

	public Upgrade(final ShipGroup ship, final Planet planet) {
		this.ship = ship;
		this.planet = planet;
	}

	public ShipGroup ship() {
		return this.ship;
	}

	public Planet planet() {
		return this.planet;
	}
}
