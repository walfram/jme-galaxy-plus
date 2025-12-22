package galaxy.domain;

import galaxy.domain.planet.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BootstrapTest {

	@Test
	void test_bootstrap_galaxy() {
		Context galaxy = new ClassicGalaxy();

		int teams = 10;
		int planetRatio = 10;

		int planetCount = teams * planetRatio;
		int homeWorlds = teams * 3;
		int otherWorlds = planetCount - homeWorlds;

		for (int i = 0; i < teams; i++) {
			Entity team = galaxy.createTeam("team-%s".formatted(i));

			Entity origin = galaxy.createHomeWorld();
			origin.put(new TeamRef(team.prop(Team.class).teamRef()));
			assertTrue(origin.has(Planet.class));

			Entity alpha = galaxy.createDaughterWorld();
			alpha.put(new TeamRef(team.prop(Team.class).teamRef()));

			Entity beta = galaxy.createDaughterWorld();
			beta.put(new TeamRef(team.prop(Team.class).teamRef()));
		}

		RandomGenerator sizeSource = RandomGenerator.getDefault();
		RandomGenerator resourcesSource = RandomGenerator.getDefault();

		while (otherWorlds > 0) {
			Entity planet = galaxy.createPlanet();
			planet.put(new Position());
			planet.put(new Size(sizeSource.nextDouble(0.1, 2500.0)));
			planet.put(new Resources(resourcesSource.nextDouble(0.1, 25.0)));
			otherWorlds--;
		}

		assertEquals(planetCount, galaxy.planets().size());

		assertEquals(homeWorlds, galaxy.query(List.of(Planet.class, TeamRef.class)).size());
	}

}
