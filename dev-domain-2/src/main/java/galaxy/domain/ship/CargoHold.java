package galaxy.domain.ship;

import galaxy.domain.Component;
import galaxy.domain.Entity;

public record CargoHold(Cargo cargo, double amount) implements Component {
}
