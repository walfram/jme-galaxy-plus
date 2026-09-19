package galaxy.ship.state;

import galaxy.Planet;
import galaxy.planet.Coordinates;
import galaxy.ship.ShipGroup;

public record InHyperspace(ShipGroup group, Planet origin, Planet destination, Coordinates coordinates) implements ShipGroupState {
}
