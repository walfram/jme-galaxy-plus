package galaxy;

import galaxy.planet.Capital;
import galaxy.planet.Colonists;

import java.util.Optional;

public interface Planet {
	String id();

	Coordinates coordinates();

	double size();
	double resources();

	double industry();
	double population();
	double materials();

	String name();

	Optional<String> owner();

	Planet withPopulation(double population);

	Planet withOwnerId(String owner);

	Capital capital();

	Colonists colonists();
}
