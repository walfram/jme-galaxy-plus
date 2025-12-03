package alt.doman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

	@Test
	void test_equality() {
		Race foo = new Race("foo");
		Race bar = new Race("foo");

		assertEquals(foo, bar);
	}

}
