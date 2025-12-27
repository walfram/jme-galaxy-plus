package galaxy.domain.order;

import galaxy.domain.Component;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.Cargo;

public record CargoLoadOrder(PlanetRef source, Cargo cargo, double quantity) implements Component {
}
