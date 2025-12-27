package domain.order;

import domain.Component;
import domain.planet.PlanetRef;

public record CargoUnloadOrder(PlanetRef destination) implements Component {
}
