package galaxy;

import galaxy.ship.Cargo;
import galaxy.ship.Location;

public interface ShipGroup {
	String id();

	String owner();

	int size();

	String type();

	TechLevels techLevels();

	Location location();

	Cargo cargo();
}
