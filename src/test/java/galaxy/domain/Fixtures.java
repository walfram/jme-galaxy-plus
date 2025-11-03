package galaxy.domain;

import galaxy.domain.planet.ClassicDaughterWorld;
import galaxy.domain.planet.ClassicHomeWorld;
import galaxy.domain.planet.Coordinates;

import java.util.List;

final class Fixtures {

	public static Race race() {
		return new Race(
				"test-race-id",
				"Test Race Name",
				List.of(
						new ClassicHomeWorld("hw", new Coordinates(0, 0, 0)),
						new ClassicDaughterWorld("dw-1", new Coordinates(1, 1, 1)),
						new ClassicDaughterWorld("dw-2", new Coordinates(2, 2, 2))
				)
		);
	}

}
