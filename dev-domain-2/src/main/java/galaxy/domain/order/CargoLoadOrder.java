package galaxy.domain.order;

import galaxy.domain.Component;
import galaxy.domain.ship.Cargo;

public record CargoLoadOrder(Cargo cargo, double quantity) implements Component {
}
