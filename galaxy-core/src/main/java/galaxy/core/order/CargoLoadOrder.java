package galaxy.core.order;

import galaxy.core.Component;
import galaxy.core.planet.PlanetRef;
import galaxy.core.ship.Cargo;

public record CargoLoadOrder(PlanetRef source, Cargo cargo, double quantity) implements Component {
}
