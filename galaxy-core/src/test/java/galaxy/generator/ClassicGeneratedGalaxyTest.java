package galaxy.generator;

import galaxy.core.Planet;
import galaxy.core.planet.DaughterWorld;
import galaxy.core.planet.HomeWorld;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicGeneratedGalaxyTest {

	private static final Logger logger = LoggerFactory.getLogger(ClassicGeneratedGalaxyTest.class);

	// given N players

	// there must be N*10 planets, including starting planets

	// HW min distance is 30.0
	// DW min distance 5.0, max distance 10.0 from HW

	// Planets distributed with "classic" ratio

	// GIANT/LARGE planet min distance 20.0, from HWs and each other
	// NORMAL planet min distance 10.0, from HWs and each other
	// SMALL/ASTEROID min distance 5.0, from HWs and each other

	// parameters:
	// - number of starting planets
	// - TORUS (default) or PLAIN map type
	// - planet distance ratio
	// - resource distribution: NORMAL or REDUCED (HW/DW x/10.0, reach x/2.5, rest x/5.0)

	private final int playerCount = 10;
	private final int planetRatio = 10;
	private final long seed = 42;

	private final GeneratedPlanets generatedGalaxy = new ClassicGeneratedGalaxy(playerCount, planetRatio, seed);

	@Test
	void test_home_worlds() {
		List<Planet> planets = generatedGalaxy.planets();
		List<Planet> homeWorlds = planets.stream().filter(p -> p.property(HomeWorld.class).isPresent()).toList();
		assertEquals(playerCount, homeWorlds.size());
	}

	@Test
	void test_daughter_worlds() {
		List<Planet> planets = generatedGalaxy.planets();
		List<Planet> daughterWorlds = planets.stream().filter(p -> p.property(DaughterWorld.class).isPresent()).toList();
		assertEquals(2 * playerCount, daughterWorlds.size());
	}

	@Test
	void test_uninhabited_planets() {
		List<Planet> planets = generatedGalaxy.planets();

		List<Planet> uninhabited = planets.stream()
				.filter(p -> p.property(HomeWorld.class).isEmpty())
				.filter(p -> p.property(DaughterWorld.class).isEmpty())
				.toList();

		int expected = (planetRatio * playerCount) - (3 * playerCount);
		assertEquals(expected, uninhabited.size());
	}

	@Test
	void print_planets() {
		generatedGalaxy.planets().forEach(p -> logger.info(p.toString()));
	}

}
