package galaxy.ship.state;

import galaxy.Planet;
import galaxy.ship.ShipGroup;

public record InUpgrade(ShipGroup group, Planet planet) implements ShipGroupState {
}
