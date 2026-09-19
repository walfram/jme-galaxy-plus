package galaxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceTest {

	@Test
	void should_return_declared_peace() {
		Race foo = new Race("foo");
		Race bar = new Race("bar");

		foo.declarePeaceTo(bar);
		assertEquals(Diplomacy.PEACE, foo.statusWith(bar));
		assertEquals(Diplomacy.WAR, bar.statusWith(foo));

		bar.declarePeaceTo(foo);
		assertEquals(Diplomacy.PEACE, bar.statusWith(foo));

		foo.declareWarTo(bar);
		assertEquals(Diplomacy.WAR, foo.statusWith(bar));
		assertEquals(Diplomacy.PEACE, bar.statusWith(foo));
	}

	@Test
	void default_diplomacy_state_is_war() {
		Race foo = new Race("foo");
		Race bar = new Race("bar");

		assertEquals(Diplomacy.WAR, foo.statusWith(bar));
	}

}
