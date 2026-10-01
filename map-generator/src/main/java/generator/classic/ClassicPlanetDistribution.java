package generator.classic;

import jme3utilities.math.noise.Generator;
import random.Distribution;
import random.WeightedDistribution;

import java.util.List;

public final class ClassicPlanetDistribution implements WeightedDistribution<PlanetType> {

	private final Distribution<PlanetType> source;

	public ClassicPlanetDistribution() {
		this.source = new Distribution<>(
				List.of(
						new PlanetType(2000.0, 2500.0, 0.05, 3.0, 6.0, 20.0),  // super giant
						new PlanetType(1000.0, 2000.0, 1.0, 10.0, 18.0, 20.0),  // giant
						new PlanetType(500.0, 1000.0, 0.05, 10.0, 50.0, 10.0),  // normal
						new PlanetType(10.0, 500.0, 5.0, 25.0, 18.0, 5.0),      // small
						new PlanetType(0.01, 10.0, 0.05, 1.0, 8.0, 5.0)        // asteroids
				)
		);
	}

	@Override
	public PlanetType pick(Generator generator) {
		return source.pick(generator);
	}

	@Override
	public List<PlanetType> items() {
		return source.items();
	}

}
