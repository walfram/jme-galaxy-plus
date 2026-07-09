package galaxy.dev;

import galaxy.core.Planet;
import galaxy.core.Race;

import java.util.List;

public class Fixtures {
	public static List<Race> testRaces() {
		return List.of(new Race("foo"), new Race("bar"), new Race("baz"));
	}

	public static List<Planet> testPlanets() {
		return List.of(new Planet(1, 2, 3, 4, 5), new Planet(6, 7, 8, 9, 10), new Planet(11, 12, 13, 14, 15));
	}
}
