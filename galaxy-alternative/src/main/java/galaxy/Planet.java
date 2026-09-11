package galaxy;

import java.util.Optional;

public interface Planet {
	String id();

	double x();
	double y();

	double size();
	double resources();

	double industry();
	double population();
	double materials();
	String name();

	Optional<String> owner();

	Planet withPopulation(double population);

	Planet withOwnerId(String owner);
}
