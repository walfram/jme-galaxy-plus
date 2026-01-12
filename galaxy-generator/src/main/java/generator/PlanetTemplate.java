package generator;

import com.jme3.math.Vector3f;
import galaxy.core.Component;
import galaxy.core.planet.Coordinates;
import galaxy.core.planet.Resources;
import galaxy.core.planet.Size;
import jme3utilities.math.noise.Generator;

import java.util.List;

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

	public List<Component> planetComponents(Vector3f coordinates, Generator random) {
		double size = random.nextDouble(minSize, maxSize);
		return List.of(
				new Coordinates(
						coordinates.x,
						coordinates.y,
						coordinates.z
				),
				new Size(size),
				new Resources(random.nextDouble(minResources, maxResources))
		);
	}
}
