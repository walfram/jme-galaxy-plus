package galaxy;

import galaxy.ship.CargoSize;
import galaxy.ship.EngineSize;
import galaxy.ship.ShieldsPower;
import galaxy.ship.Weapons;

public interface ShipType {
	String name();

	EngineSize engineSize();
	Weapons weapons();
	ShieldsPower shieldsPower();
	CargoSize cargoSize();

	Double mass();
}
