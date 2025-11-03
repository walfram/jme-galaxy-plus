package galaxy.generator;

import com.jme3.math.Vector3f;
import galaxy.domain.planet.*;
import jme3utilities.math.noise.Generator;

import java.util.UUID;

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

	private String name(double size) {
		if (size > 2000.0) {
			return "SLG-%s";
		} else if (size > 1000.0) {
			return "LG-%s";
		} else if (size > 500.0) {
			return "AVG-%s";
		} else if (size > 10.0) {
			return "SM-%s";
		}

		return "AST-%s";
	}

	public Planet createAtCoordinates(Vector3f picked, Generator random) {
		double size = random.nextDouble(minSize, maxSize);
		return new Planet(
				name(size).formatted(UUID.randomUUID().toString().substring(0, 4)),
				new Coordinates(picked),
				new Size(size),
				new Resources(random.nextDouble(minResources, maxResources)),
				new Population(0),
				new Industry(0),
				new Materials(0)
		);
	}
}
