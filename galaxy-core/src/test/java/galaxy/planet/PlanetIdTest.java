package galaxy.planet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanetIdTest {

	@Test
	void test_equality() {
		PlanetId a = new PlanetId("foo");
		PlanetId b = new PlanetId("foo");
		assertEquals(a, b);
	}

}
