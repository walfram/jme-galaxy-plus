package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiplomacyTest {

	@Test
	void test_diplomacy_can_be_without_races() {
		Diplomacy diplomacy = new Diplomacy();

		assertEquals(DiplomaticStatus.WAR, diplomacy.status(new Race("foo", "foo")));
	}

	@Test
	void test_initial_diplomacy_is_all_war() {
		List<Race> races = Fixtures.races();
		Diplomacy diplomacy = new Diplomacy(races);

		for (Race race: races) {
			assertEquals(DiplomaticStatus.WAR, diplomacy.status(race));
		}
	}

}

