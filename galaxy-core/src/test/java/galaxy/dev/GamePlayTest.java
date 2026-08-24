package galaxy.dev;

import galaxy.Fixtures;
import galaxy.core.*;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;
import galaxy.core.state.ClassicGalaxy;
import galaxy.order.DefineShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// used to do some refactoring
public class GamePlayTest {

	private GameState state;
	private Race foo;
	private Race bar;

	@BeforeEach
	void setup() {
		foo = new Race("foo");
		bar = new Race("bar");

		Planet fooA = Fixtures.testRandomPlanet("foo-a");
		Planet fooB = Fixtures.testRandomPlanet("foo-b");
		Planet fooC = Fixtures.testRandomPlanet("foo-c");

		Planet barA = Fixtures.testRandomPlanet("bar-a");
		Planet barB = Fixtures.testRandomPlanet("bar-b");
		Planet barC = Fixtures.testRandomPlanet("bar-c");

		Planet a = Fixtures.testRandomPlanet("a");
		Planet b = Fixtures.testRandomPlanet("b");
		Planet c = Fixtures.testRandomPlanet("c");
		Planet d = Fixtures.testRandomPlanet("d");

		state = new ClassicGalaxy(
				List.of(foo, bar),
				List.of(fooA, fooB, fooC, barA, barB, barC, a, b, c, d)
		);

		state.colonizePlanet(foo, fooA);
		state.colonizePlanet(foo, fooB);
		state.colonizePlanet(foo, fooC);

		state.colonizePlanet(bar, barA);
		state.colonizePlanet(bar, barB);
		state.colonizePlanet(bar, barC);
	}

	@Test
	void test_early_gameplay() {
		// TURN #1

		// FOO defines drone ship type,
		// FOO starts colonizer drone production on foo-b
		// FOO starts engine research on foo-a
		// FOO starts cap production on foo-c
		// FOO declares peace with BAR

		// BAR defines armed drone ship type,
		// BAR starts armed colonizer drone production on bar-c
		// BAR starts engine research on bar-a
		// BAR starts weapons research on bar-b
		// BAR declares peace with FOO

		// TURN #2
	}

	@Test
	void race_creates_ship_type() {
		assertEquals(0, foo.shipTypes().size());

		ShipType fooDrone = new ShipType(new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new Cargo(0.0), "drone");
		foo.shipTypes().add(fooDrone);

		assertEquals(1, foo.shipTypes().size());

		assertEquals(0, bar.shipTypes().size());

		ShipType barDrone = new ShipType(new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new Cargo(0.0), "drone");
		bar.shipTypes().add(barDrone);

		assertEquals(1, bar.shipTypes().size());
	}

	@Test
	void race_colonized_planet() {
		assertEquals(3, state.racePlanets(foo.id()).size());

		Planet a = state.findPlanet(new Id("a"));
		state.colonizePlanet(foo, a);

		assertEquals(4, state.racePlanets(foo.id()).size());
		assertEquals(4, foo.planets().size());

		assertEquals(3, state.racePlanets(bar.id()).size());

		Planet b = state.findPlanet(new Id("b"));
		state.colonizePlanet(bar, b);
		Planet c = state.findPlanet(new Id("c"));
		state.colonizePlanet(bar, c);

		assertEquals(5, state.racePlanets(bar.id()).size());
		assertEquals(5, bar.planets().size());
	}

	@Test
	void test_initial_state() {
		List<Planet> planets = foo.planets();
		assertEquals(3, planets.size());

		List<Planet> racePlanets = state.racePlanets(foo.id());
		assertEquals(3, racePlanets.size());

		assertEquals(new HashSet<>(planets), new HashSet<>(racePlanets));
	}

}
