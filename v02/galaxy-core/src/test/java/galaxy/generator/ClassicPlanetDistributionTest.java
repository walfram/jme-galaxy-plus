package galaxy.generator;

import jme3utilities.math.noise.Generator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassicPlanetDistributionTest {

	private static final Logger logger = LoggerFactory.getLogger(ClassicPlanetDistributionTest.class);

	@ParameterizedTest
	@ValueSource(ints = {70, 100, 1000, 10_000, 100_000, 1_000_000})
	void test_distribution_at_N_planets(int planetCount) {
		WeightedDistribution<PlanetType> d = new ClassicPlanetDistribution();

		List<PlanetType> types = new ArrayList<>(planetCount);
		Generator generator	= new Generator(42);

		for (int i = 0; i < planetCount; i++) {
			PlanetType picked = d.pick(generator);
			types.add(picked);
		}

		for (PlanetType type : d.items()) {
			long count = types.stream().filter(t -> t == type).count();
			logger.info("count = {} for type = {}", count, type);

			double p = type.weight() / 100.0;
			double expectedCount = p * planetCount;
			double stdDev = Math.sqrt(planetCount * p * (1 - p));

			double lowerBound = expectedCount - 3 * stdDev;
			double upperBound = expectedCount + 3 * stdDev;

			assertTrue(count >= lowerBound && count <= upperBound,
					"expected ~%.1f (±%.1f), got %d for type %s".formatted(expectedCount, 3 * stdDev, count, type));
		}

	}

}
