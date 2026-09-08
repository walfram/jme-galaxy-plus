package galaxy;

import galaxy.ship.Weapons;

public interface ShipType {
	double engines();
	Weapons weapons();
	double shields();
	double cargo();
}
