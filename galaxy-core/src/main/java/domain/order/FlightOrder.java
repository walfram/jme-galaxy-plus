package domain.order;

import domain.Component;
import domain.planet.PlanetRef;

public record FlightOrder(PlanetRef origin, PlanetRef destination) implements Component {
}
