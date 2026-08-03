package galaxy.generator;

import com.jme3.math.Vector3f;
import galaxy.core.Planet;
import jme3utilities.math.noise.Generator;

public record PlanetType(
		double minSize, double maxSize,
		double minResources, double maxResources,
		double weight,
		double minDistance
) implements Weighted {
	@Override
	public double weight() {
		return weight;
	}

	public Planet generate(int idx, Vector3f coords, Generator generator) {
		return new Planet(idx, coords.x, coords.y, generator.nextDouble(minSize, maxSize), generator.nextDouble(minResources, maxResources));
	}
}
