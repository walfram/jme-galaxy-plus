package galaxy.generator;

import com.jme3.math.Vector3f;
import galaxy.domain.*;
import jme3utilities.math.noise.Generator;

import java.util.UUID;

public record PlanetTemplate(
		float minSize, float maxSize,
		float minResources, float maxResources,
		float frequency,
		float minDistance
) implements Weighted {
	@Override
	public float weight() {
		return frequency;
	}

	private String name(float size) {
		if (size > 2000f) {
			return "SLG-%s";
		} else if (size > 1000f) {
			return "LG-%s";
		} else if (size > 500f) {
			return "AVG-%s";
		} else if (size > 10f) {
			return "SM-%s";
		}

		return "AST-%s";
	}

	public Planet createAtCoordinates(Vector3f picked, Generator random) {
		float size = random.nextFloat(minSize, maxSize);
		return new Planet(
				name(size).formatted(UUID.randomUUID().toString().substring(0, 4)),
				new Coordinates(picked),
				new Size(size),
				new Resources(random.nextFloat(minResources, maxResources)),
				new Population(0f),
				new Industry(0f)
		);
	}
}
