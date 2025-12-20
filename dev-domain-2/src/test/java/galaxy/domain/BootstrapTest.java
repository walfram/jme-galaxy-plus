package galaxy.domain;

import galaxy.domain.planet.*;
import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
			Entity origin = galaxy.createPlanet();
			origin.put(new Position());
			origin.put(new TeamRef(null));
			origin.put(new Size(1000.0));
			origin.put(new Resources(10.0));
			origin.put(new HomeWorldTag());
			origin.put(new Population(1000.0));
			origin.put(new Industry(1000.0));

			Entity alpha = galaxy.createPlanet();
			alpha.put(new Position());
			alpha.put(new TeamRef(null));
			alpha.put(new Size(500.0));
			alpha.put(new Resources(10.0));
			alpha.put(new DaughterWorld());
			alpha.put(new Population(500.0));
			alpha.put(new Industry(500.0));

			Entity beta = galaxy.createPlanet();
			beta.put(new Position());
			beta.put(new TeamRef(null));
			beta.put(new Size(500.0));
			beta.put(new Resources(10.0));
			beta.put(new DaughterWorld());
			beta.put(new Population(500.0));
			beta.put(new Industry(500.0));
		}

		RandomGenerator sizeSource = RandomGenerator.getDefault();
		RandomGenerator resourcesSource = RandomGenerator.getDefault();

		while (otherWorlds > 0) {
			Entity planet = galaxy.createPlanet();
			planet.put(new Position());
			planet.put(new TeamRef(null));
			planet.put(new Size(sizeSource.nextDouble(0.1, 2500.0)));
			planet.put(new Resources(resourcesSource.nextDouble(0.1, 25.0)));
			otherWorlds--;
		}

		assertEquals(planetCount, galaxy.planets().size());
	}

}
