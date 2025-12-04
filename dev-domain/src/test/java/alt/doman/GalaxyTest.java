package alt.doman;

import alt.doman.planet.Planet;
import alt.doman.planet.PlanetView;
import alt.doman.planet.UnknownPlanet;
import alt.doman.planet.VisiblePlanet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyTest {

	private Galaxy galaxy;

	@BeforeEach
	void setup() {
		List<Race> races = List.of(
				new Race("foo"),
				new Race("bar"),
				new Race("baz")
		);

		List<Planet> planets = List.of(
				new Planet(1, null, null, null),
				new Planet(2, null, null, null),
				new Planet(3, null, null, null),
				new Planet(4, null, null, null),
				new Planet(5, null, null, null)
		);

		galaxy = new Galaxy(races, planets);

		races.get(0).claim(planets.get(0));
		races.get(1).claim(planets.get(2));
		races.get(2).claim(planets.get(4));
	}

	@Test
	void race_should_see_own_planets_and_only_coordinates_for_unknown_planets() {
		Race race = galaxy.race("foo");

		List<PlanetView> planets = galaxy.planets(race);

		long visible = planets.stream().filter(p -> p instanceof VisiblePlanet).count();
		assertEquals(1, visible);

		long unknown = planets.stream().filter(p -> p instanceof UnknownPlanet).count();
		assertEquals(4, unknown);
	}

	@Test
	void when_planet_is_lost_then_it_is_removed_from_race() {
		// race "foo" has ships at race "bar"'s planet
		// ships bomb planet to 0
		// race "bar" loses planet
	}

}
