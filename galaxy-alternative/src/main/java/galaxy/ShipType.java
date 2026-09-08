package galaxy;

import galaxy.ship.Weapons;

public interface ShipType {
	String name();
	double engines();
	Weapons weapons();
	double shields();
	double cargo();
}
