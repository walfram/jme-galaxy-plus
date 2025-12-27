package domain.order;

import domain.Component;
import domain.planet.PlanetRef;
import domain.ship.Cargo;

public record CargoLoadOrder(PlanetRef source, Cargo cargo, double quantity) implements Component {
}
