package distribution;

import galaxy.Planet;
import galaxy.planet.Coordinates;
import galaxy.planet.PlanetId;
import galaxy.planet.Resources;
import galaxy.planet.Size;
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

	public Planet generate(PlanetId id, Vector2d coords, Generator generator) {
		return new Planet(
				id,
				new Coordinates(coords.x(), coords.y()),
				new Size(generator.nextDouble(minSize, maxSize)),
				new Resources(generator.nextDouble(minResources, maxResources))
		);
	}
}
