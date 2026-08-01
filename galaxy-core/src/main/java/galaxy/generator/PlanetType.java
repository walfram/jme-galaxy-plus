package galaxy.generator;

import com.simsilica.mathd.Vec3d;
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

	public Planet generate(int idx, Vec3d coords, Generator generator) {
		return new Planet(idx, coords.x, coords.y, generator.nextDouble(minSize, maxSize), generator.nextDouble(minResources, maxResources));
	}
}
