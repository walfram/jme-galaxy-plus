package galaxy.domain.order;

import galaxy.domain.Component;
import galaxy.domain.planet.PlanetRef;

public record CargoUnloadOrder(PlanetRef destination) implements Component {
}
