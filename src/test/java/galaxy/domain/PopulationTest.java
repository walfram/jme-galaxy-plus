package galaxy.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {

	@Test
	void test_population_value_cant_be_negative() {
		Population subjects = new Population(100.0);

		subjects.update(-100.0);
		assertEquals(0, subjects.value());

		subjects.update(-100.0);
		assertEquals(0, subjects.value());
	}

}
