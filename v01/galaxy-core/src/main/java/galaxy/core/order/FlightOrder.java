package galaxy.core.order;

import galaxy.core.Component;
import galaxy.core.planet.PlanetRef;

public record FlightOrder(PlanetRef origin, PlanetRef destination) implements Component {
}
