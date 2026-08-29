package galaxy.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

	@Test
	void test_initial_state() {
		Race race = new Race("crash-test-dummy");

		assertEquals(1.0, race.techLevel(Technology.ENGINES));
		assertEquals(1.0, race.techLevel(Technology.WEAPONS));
		assertEquals(1.0, race.techLevel(Technology.SHIELDS));
		assertEquals(1.0, race.techLevel(Technology.CARGO));
	}

}
