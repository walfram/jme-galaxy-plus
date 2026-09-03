package distribution;

import galaxy.Id;
import galaxy.Planet;
import galaxy.planet.properties.Resources;
import galaxy.planet.properties.Size;
import galaxy.planet.properties.Transform;
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
		return new Planet(
				id,
				new Transform(coords.x(), coords.y()),
				new Size(generator.nextDouble(minSize, maxSize)),
				new Resources(generator.nextDouble(minResources, maxResources))
		);
	}
}
