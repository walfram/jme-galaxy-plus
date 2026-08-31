package distribution.classic;

import distribution.PlanetType;
import distribution.WeightedDistribution;
import jme3utilities.math.noise.Generator;

import java.util.List;

public class ClassicPlanetDistribution implements WeightedDistribution<PlanetType> {

	private final List<PlanetType> planetTypes = List.of(
			new PlanetType(2000.0, 2500.0, 0.05, 3.0, 6.0, 20.0), 	// super giant
			new PlanetType(1000.0, 2000.0, 1.0, 10.0, 18.0, 20.0), 	// giant
			new PlanetType(500.0, 1000.0, 0.05, 10.0, 50.0, 10.0), 	// normal
			new PlanetType(10.0, 500.0, 5.0, 25.0, 18.0, 5.0), 			// small
			new PlanetType(0.01, 10.0, 0.05, 1.0, 8.0, 5.0) 				// asteroids
	);

	@Override
	public PlanetType pick(Generator generator) {
		double sum = planetTypes.stream().mapToDouble(PlanetType::weight).sum();

		double e = generator.nextDouble(sum);
		for (PlanetType planetType : planetTypes) {
			if (e < planetType.weight()) {
				return planetType;
			}

			e -= planetType.weight();
		}

		throw new RuntimeException("Could not pick a planet template");
	}

	@Override
	public List<PlanetType> items() {
		return List.copyOf(planetTypes);
	}

}
