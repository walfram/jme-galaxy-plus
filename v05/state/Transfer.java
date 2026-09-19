package galaxy.state;

import galaxy.Planet;
import galaxy.Race;
import galaxy.ships.ShipGroup;

public final class Transfer {
	private final ShipGroup ship;
	private final Planet planet;
	private final Race from;
	private final Race to;

	public Transfer(final ShipGroup ship, final Planet planet, final Race from, final Race to) {
		this.ship = ship;
		this.planet = planet;
		this.from = from;
		this.to = to;
	}

	public ShipGroup ship() {
		return this.ship;
	}

	public Planet planet() {
		return this.planet;
	}

	public Race from() {
		return this.from;
	}

	public Race to() {
		return this.to;
	}
}
