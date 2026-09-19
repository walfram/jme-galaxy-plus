package galaxy.state;

import galaxy.ships.ShipGroup;

interface ShGrBucket extends Iterable<ShipGroup> {
	boolean has(ShipGroup group);
}
