package distribution;

import galaxy.CoordinatesOf;
import galaxy.Id;
import galaxy.Planet;
import galaxy.decorators.PlanetOf;
import hex.grid.Vector2d;
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
		return new PlanetOf(
				id.value(),
				new CoordinatesOf(coords.x(), coords.y()),
				generator.nextDouble(minSize, maxSize),
				generator.nextDouble(minResources, maxResources)
		);
	}
}
