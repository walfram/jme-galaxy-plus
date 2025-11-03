package galaxy.domain;

import galaxy.domain.planet.ClassicDaughterWorld;
import galaxy.domain.planet.ClassicHomeWorld;
import galaxy.domain.planet.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

	@Test
	void test_race_init() {
		Race race = Fixtures.race();

		assertEquals(3, race.planets().size());

		assertEquals(4, race.technologies().size());

		assertEquals(0, race.shipTemplates().size());
		assertEquals(0, race.ships().size());

		assertEquals(0, race.routes().size());
	}

}
