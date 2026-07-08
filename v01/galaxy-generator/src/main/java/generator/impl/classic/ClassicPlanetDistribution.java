package generator.impl.classic;

import generator.PlanetTemplate;
import generator.WeightedDistribution;

import java.util.List;
import java.util.random.RandomGenerator;

public class ClassicPlanetDistribution implements WeightedDistribution<PlanetTemplate> {

	private final List<PlanetTemplate> templates = List.of(
			new PlanetTemplate(2000, 2500, 0.05, 3, 6, 20),
			new PlanetTemplate(1000, 2000, 1, 10, 18, 20),
			new PlanetTemplate(500, 1000, 0.05, 10, 50, 10),
			new PlanetTemplate(10, 500, 5, 25, 18, 5),
			new PlanetTemplate(0, 1, 0.05, 1, 8, 5)
	);

	@Override
	public PlanetTemplate pick(RandomGenerator random) {
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
