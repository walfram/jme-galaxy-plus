package galaxy.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechLevelsTest {

	@Test
	void test_equality() {
		TechLevels left = new TechLevels();
		TechLevels right = new TechLevels();
		assertEquals(left, right);

		TechLevels other = new TechLevels(1.0, 1.0, 1.0, 1.0);
		assertEquals(left, other);
		assertEquals(right, other);

		TechLevels levels = new TechLevels(2, 1, 1, 1);
		assertNotEquals(left, levels);

		assertNotEquals(new TechLevels(1, 2, 1, 1), levels);
	}

}
