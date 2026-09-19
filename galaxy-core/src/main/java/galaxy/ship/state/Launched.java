package galaxy.ship.state;

import galaxy.Planet;
import galaxy.ship.ShipGroup;

public record Launched(ShipGroup group, Planet origin, Planet destination) implements ShipGroupState {
}
