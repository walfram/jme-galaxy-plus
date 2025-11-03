package galaxy.generator.simple;

import galaxy.domain.planet.Planet;
import galaxy.generator.SimpleSeedSource;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimplePlanetGeneratorTest {

	@Test
	void test_planet_count() {
		SimplePlanetGenerator simple = new SimplePlanetGenerator(new Generator(42), 10, 10, new SimpleSeedSource(1024, 128f, 42));

		int planetCount = simple.planetCount();
		assertEquals(100, planetCount);

		List<Planet> planets = simple.planets();
		assertEquals(100, planets.size());
	}

}
