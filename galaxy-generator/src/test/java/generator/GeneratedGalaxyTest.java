package generator;

import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.planet.*;
import galaxy.core.team.GalaxyView;
import generator.sources.SimpleSeedSource;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneratedGalaxyTest {

	@Test
	void should_generate_classic_galaxy() {
		int teamCount = 10;
		int planetRatio = 10;
		SeedSource seedSource = new SimpleSeedSource(32768, 192.0);
		long seed = 42l;

		GeneratedGalaxy generatedGalaxy = new ClassicGeneratedGalaxy(teamCount, planetRatio, seedSource, seed);
		Context galaxy = generatedGalaxy.generate();

		assertEquals(10, galaxy.teams().size());
		assertEquals(100, galaxy.planets().size());

		for (Entity planet : galaxy.planets().values()) {
			assertNotNull(planet.prop(Coordinates.class));
			assertNotNull(planet.prop(Size.class));
			assertNotNull(planet.prop(Resources.class));
		}

		List<Entity> homeWorlds = galaxy.query(List.of(HomeWorldTag.class));
		assertEquals(teamCount, homeWorlds.size());

		List<Entity> daughterWorlds = galaxy.query(List.of(DaughterWorldTag.class));
		assertEquals(2 * teamCount, daughterWorlds.size());

		for (Entity team: galaxy.teams().values()) {
			int knownGalaxySize = team.prop(GalaxyView.class).knownGalaxySize();
			assertEquals(teamCount * planetRatio, knownGalaxySize);

			Collection<PlanetView> ownedPlanets = team.prop(GalaxyView.class).ownedPlanets();
			assertEquals(3, ownedPlanets.size());
		}
	}

}
