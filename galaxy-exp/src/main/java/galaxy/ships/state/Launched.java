package galaxy.ships.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

public record Launched(ShipGroup group, Planet origin, Planet destination) implements ShipGroupState {
}
