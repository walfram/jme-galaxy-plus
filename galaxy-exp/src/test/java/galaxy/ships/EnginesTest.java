package galaxy.ships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnginesTest {

	@Test
	void test_engines_tech_level_2() {
		Engines engines = new EnginesOf(1.0, new TechLevel(2.0));
		assertEquals(40.0, engines.power());
	}

	@Test
	void test_engines_tech_level_1() {
		Engines engines = new EnginesOf(1.0);
		assertEquals(20.0, engines.power());
	}

}
