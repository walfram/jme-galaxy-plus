package galaxy.ships.state;

import galaxy.Planet;
import galaxy.Race;
import galaxy.ships.ShipGroup;

public record InTransfer(ShipGroup group, Planet planet, Race from, Race to) implements ShipGroupState {
}
