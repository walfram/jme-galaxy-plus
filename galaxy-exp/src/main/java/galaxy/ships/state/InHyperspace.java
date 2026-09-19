package galaxy.ships.state;

import galaxy.Coordinates;
import galaxy.Planet;
import galaxy.ships.ShipGroup;

public record InHyperspace(ShipGroup group, Planet origin, Planet destination, Coordinates coordinates) implements ShipGroupState {
}
