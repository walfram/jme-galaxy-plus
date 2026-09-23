package galaxy.ships.state;

import galaxy.planet.Coordinates;
import galaxy.planet.Planet;
import galaxy.ships.ShipGroup;

public record InHyperspace(ShipGroup group, Planet origin, Planet destination, Coordinates coordinates) implements ShipGroupState {
}
