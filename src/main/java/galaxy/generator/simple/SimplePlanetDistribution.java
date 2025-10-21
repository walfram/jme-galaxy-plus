package galaxy.generator.simple;

import galaxy.generator.PlanetTemplate;
import galaxy.generator.WeightedDistribution;
import jme3utilities.math.noise.Generator;

import java.util.List;

public class SimplePlanetDistribution implements WeightedDistribution<PlanetTemplate> {

	private final List<PlanetTemplate> templates = List.of(
			new PlanetTemplate(2000f, 2500f, 0.05f, 3f, 6, 20f),
			new PlanetTemplate(1000f, 2000f, 1f, 10f, 18, 20f),
			new PlanetTemplate(500f, 1000f, 0.05f, 10f, 50, 10f),
			new PlanetTemplate(10f, 500f, 5f, 25f, 18, 5f),
			new PlanetTemplate(0f, 1f, 0.05f, 1f, 8, 5f)
	);

	@Override
	public PlanetTemplate pick(Generator random) {
		double sum = templates.stream().mapToDouble(PlanetTemplate::weight).sum();

		double e = random.nextDouble(sum);
		for (PlanetTemplate template : templates) {
			if (e < template.weight()) {
				return template;
			}

			e -= template.weight();
		}

		throw new RuntimeException("Could not pick a planet template");
	}
}
