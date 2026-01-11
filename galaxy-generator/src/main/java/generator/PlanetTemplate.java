package generator;

import com.jme3.math.Vector3f;
import galaxy.core.Entity;
import galaxy.core.planet.*;
import jme3utilities.math.noise.Generator;

public record PlanetTemplate(
		double minSize, double maxSize,
		double minResources, double maxResources,
		double frequency,
		double minDistance
) implements Weighted {

	@Override
	public double weight() {
		return frequency;
	}

	public Entity createAtCoordinates(Vector3f coordinates, Generator random, Long id) {
		double size = random.nextDouble(minSize, maxSize);
		return new Entity(
				new Planet(),
				new PlanetRef(id),
				new Coordinates(
						coordinates.x,
						coordinates.y,
						coordinates.z
				),
				new Size(size),
				new Resources(random.nextDouble(minResources, maxResources)),
				new Population(0),
				new Industry(0),
				new Materials(0)
		);
	}
}
