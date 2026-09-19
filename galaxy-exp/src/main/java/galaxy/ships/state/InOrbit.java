package galaxy.ships.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

public record InOrbit(ShipGroup group, Planet planet) implements ShipGroupState {
}
