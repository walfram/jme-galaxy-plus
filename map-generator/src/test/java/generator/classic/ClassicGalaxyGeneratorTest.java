package generator.classic;

import galaxy.Planet;
import generator.GeneratedPlanets;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicGalaxyGeneratorTest {

	private final int raceCount = 10;
	private final int planetRatio = 10;
	private final long seed = 127;

	private final GeneratedPlanets generatedPlanets = new ClassicGeneratedPlanets(raceCount, planetRatio, seed);

	@Test
	void should_generate_correct_number_of_home_worlds() {
		List<List<Planet>> homeWorlds = generatedPlanets.homeworlds();
		assertEquals(raceCount, homeWorlds.size());

		for (List<Planet> homeWorld : homeWorlds) {
			assertEquals(3, homeWorld.size());
		}
	}

	@Test
	void should_generate_correct_number_of_planets() {
		List<Planet> planets = generatedPlanets.allPlanets();
		assertEquals(raceCount * planetRatio, planets.size());
	}

}
