package galaxy.decorators;

import galaxy.ShipType;
import galaxy.ship.CargoSize;
import galaxy.ship.EngineSize;
import galaxy.ship.ShieldsPower;
import galaxy.ship.Weapons;

public record ShipTypeOf(String name, EngineSize engineSize, Weapons weapons, ShieldsPower shieldsPower,
												 CargoSize cargoSize) implements ShipType {
}
