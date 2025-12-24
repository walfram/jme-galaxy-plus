package galaxy.domain.order;

import galaxy.domain.Component;
import galaxy.domain.planet.PlanetRef;

public record FlightOrder(PlanetRef origin, PlanetRef destination) implements Component {
}
