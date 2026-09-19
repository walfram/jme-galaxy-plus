package galaxy.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

public final class Voyage {
	private final ShipGroup ship;
	private final Coordinates position;
	private final Planet destination;

	public Voyage(final ShipGroup ship, final Coordinates position, final Planet destination) {
		this.ship = ship;
		this.position = position;
		this.destination = destination;
	}

	public ShipGroup ship() {
		return this.ship;
	}

	public Planet destination() {
		return this.destination;
	}

	public Voyage advanced() {
		return new Voyage(
				this.ship,
				this.position.movedTowards(this.destination.coordinates(), this.ship.speed()),
				this.destination
		);
	}

	public boolean arrived() {
		return this.position.equals(this.destination.coordinates());
	}
}
