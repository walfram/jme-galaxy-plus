package galaxy.domain;

import galaxy.domain.planet.ClassicDaughterWorld;
import galaxy.domain.planet.ClassicHomeWorld;
import galaxy.domain.planet.Coordinates;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.TechnologyProduction;
import galaxy.domain.technology.EnginesTechnology;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class GalaxyTest {

	@Test
	void test_race_starts_research() {
		Race foo = new Race("foo", "foo", List.of(
				new ClassicHomeWorld("foo-primary", new Coordinates(0, 0, 0)),
				new ClassicDaughterWorld("foo-secondary-1", new Coordinates(0, 0, 1)),
				new ClassicDaughterWorld("foo-secondary-2", new Coordinates(0, 0, 2))
		));

		Race bar = new Race("bar", "bar", List.of(
				new ClassicHomeWorld("bar-primary", new Coordinates(0, 0, 10)),
				new ClassicDaughterWorld("bar-secondary-1", new Coordinates(0, 0, 11)),
				new ClassicDaughterWorld("bar-secondary-2", new Coordinates(0, 0, 12))
		));

		Race baz = new Race("baz", "baz", List.of(
				new ClassicHomeWorld("baz-primary", new Coordinates(0, 0, 20)),
				new ClassicDaughterWorld("baz-secondary-1", new Coordinates(0, 0, 21)),
				new ClassicDaughterWorld("baz-secondary-2", new Coordinates(0, 0, 22))
		));

		List<Race> races = List.of(foo, bar, baz);

		List<Planet> planets = Stream.of(
				foo.planets(), bar.planets(), baz.planets(),
				Fixtures.fixedPlanets(24)
		).flatMap(List::stream).toList();

		Galaxy galaxy = new ClassicGalaxy(races, planets);

		foo.planet("foo-secondary-1").ifPresent(p -> p.startProduction(new TechnologyProduction(p, new EnginesTechnology())));
	}

}
