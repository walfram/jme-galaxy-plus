package galaxy.core.order;

import galaxy.core.Component;
import galaxy.core.planet.PlanetRef;

public record CargoUnloadOrder(PlanetRef destination) implements Component {
}
