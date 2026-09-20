package galaxy.order;

import galaxy.Diplomacy;
import galaxy.context.GameContext;
import galaxy.Order;
import galaxy.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class DiplomacyChangeTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	public void should_change_diplomacy_to_war() {
		Race race = new Race("foo");
		Race otherRace = new Race("bar");

		// by default it is WAR
		assertEquals(Diplomacy.WAR, race.statusWith(otherRace));

		Order order = new DiplomacyChange(race, otherRace, Diplomacy.WAR);
		order.applyTo(context);

		assertEquals(Diplomacy.WAR, race.statusWith(otherRace));
	}

	@Test
	public void should_change_diplomacy_to_peace() {
		Race race = new Race("foo");
		Race otherRace = new Race("bar");

		Order order = new DiplomacyChange(race, otherRace, Diplomacy.PEACE);
		order.applyTo(context);

		assertEquals(Diplomacy.PEACE, race.statusWith(otherRace));
	}

}
