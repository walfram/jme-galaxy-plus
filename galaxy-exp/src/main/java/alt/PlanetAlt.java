package alt;

import galaxy.planet.*;

public interface PlanetAlt {

	Size size();
	Coordinates coordinates();

	// economy
	Population population();
	IndustryOf industry();

	// stockpiles
	Materials materials();
	Colonists colonists();
	Capital capital();

	// mutations
	Planet growPopulation();
	Planet unload(Colonists colonists);
	Planet extract(Colonists colonists);

	Planet unload(Capital capital);
	Planet extract(Capital capital);

	Planet unload(Materials materials);
	Planet extract(Materials materials);

}
