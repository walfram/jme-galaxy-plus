package generator;

import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.planet.Coordinates;
import galaxy.core.planet.Resources;
import galaxy.core.planet.Size;
import generator.sources.SimpleSeedSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneratedGalaxyTest {

	@Test
	void should_generate_classic_galaxy() {
		int teamCount = 10;
		int planetRatio = 10;
		SeedSource seedSource = new SimpleSeedSource(32768, 192.0);

		GeneratedGalaxy generatedGalaxy = new ClassicGeneratedGalaxy(teamCount, planetRatio, seedSource);
		Context galaxy = generatedGalaxy.generate();

		assertEquals(10, galaxy.teams().size());
		assertEquals(100, galaxy.planets().size());

		for (Entity planet: galaxy.planets().values()) {
			assertNotNull(planet.prop(Coordinates.class));
			assertNotNull(planet.prop(Size.class));
			assertNotNull(planet.prop(Resources.class));
		}
	}

}
