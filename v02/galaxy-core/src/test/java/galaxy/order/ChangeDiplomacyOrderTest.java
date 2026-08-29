package galaxy.order;

import galaxy.core.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ChangeDiplomacyOrderTest {

	@Test
	void should_return_war_as_default_diplomacy() {
		Race foo = new Race("foo");
		Race bar = new Race("bar");

		assertEquals(DiplomaticStatus.WAR, foo.diplomacyWith(bar));
		assertEquals(DiplomaticStatus.WAR, bar.diplomacyWith(foo));
	}

	@Test
	void should_set_diplomacy_to_war() {
		Race foo = new Race("foo");
		Race bar = new Race("bar");

		GameState state = mock(GameState.class);

		Order order = new ChangeDiplomacyOrder(foo, bar, DiplomaticStatus.WAR);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		assertEquals(DiplomaticStatus.WAR, foo.diplomacyWith(bar));
	}

	@Test
	void should_set_diplomacy_to_peace() {
		Race foo = new Race("foo");
		Race bar = new Race("bar");

		GameState state = mock(GameState.class);

		Order order = new ChangeDiplomacyOrder(foo, bar, DiplomaticStatus.PEACE);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		assertEquals(DiplomaticStatus.PEACE, foo.diplomacyWith(bar));
	}

}
