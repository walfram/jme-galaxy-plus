package galaxy.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

public final class Orbit {
	private final ShipGroup ship;
	private final Planet planet;

	public Orbit(final ShipGroup ship, final Planet planet) {
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
