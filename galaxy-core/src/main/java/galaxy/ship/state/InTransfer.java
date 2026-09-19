package galaxy.ship.state;

import galaxy.Planet;
import galaxy.Race;
import galaxy.ship.ShipGroup;

public record InTransfer(ShipGroup group, Planet planet, Race from, Race to) implements ShipGroupState {
}
