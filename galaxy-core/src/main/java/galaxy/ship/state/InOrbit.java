package galaxy.ship.state;

import galaxy.Planet;
import galaxy.ship.ShipGroup;

public record InOrbit(ShipGroup group, Planet planet) implements ShipGroupState {
}
