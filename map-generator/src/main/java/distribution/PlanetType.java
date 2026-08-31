package distribution;

import galaxy.Id;
import hex.grid.Vector2d;
import galaxy.planet.Planet;
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

	public Planet generate(Id id, Vector2d coords, Generator generator) {
		return new Planet(id, coords.x(), coords.y(), generator.nextDouble(minSize, maxSize), generator.nextDouble(minResources, maxResources));
	}
}
