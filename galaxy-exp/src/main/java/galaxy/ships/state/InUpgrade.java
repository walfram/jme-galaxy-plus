package galaxy.ships.state;

import galaxy.planet.Planet;
import galaxy.ships.ShipGroup;

public record InUpgrade(ShipGroup group, Planet planet) implements ShipGroupState {
}
