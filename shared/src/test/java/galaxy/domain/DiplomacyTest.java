package galaxy.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiplomacyTest {

	@Test
	void test_cannot_declare_peach_to_self() {
		List<Race> races = Fixtures.races();
		Diplomacy diplomacy = new Diplomacy(races);
		Race first = races.getFirst();

		assertThrows(IllegalArgumentException.class, () -> diplomacy.declarePeace(first, first));
	}

	@Test
	void test_cannot_declare_war_to_self() {
		List<Race> races = Fixtures.races();
		Diplomacy diplomacy = new Diplomacy(races);
		Race first = races.getFirst();

		assertThrows(IllegalArgumentException.class, () -> diplomacy.declareWar(first, first));
	}

	@Test
	void test_there_is_no_status_to_self() {
		List<Race> races = Fixtures.races();
		Diplomacy diplomacy = new Diplomacy(races);
		Race first = races.getFirst();

		assertThrows(IllegalArgumentException.class, () -> diplomacy.status(first, first));
	}

	@Test
	void test_initial_diplomacy_is_all_war() {
		List<Race> races = Fixtures.races();
		Diplomacy diplomacy = new Diplomacy(races);

		for (int outer = 0; outer < races.size(); outer++) {
			for (int inner = 0; inner < races.size(); inner++) {
				if (outer == inner)
					continue;

				Race outerRace = races.get(outer);
				Race innerRace = races.get(inner);

				assertEquals(DiplomaticStatus.WAR, diplomacy.status(outerRace, innerRace));
			}
		}
	}

}
