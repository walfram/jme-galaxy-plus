package domain.planet;

import domain.planet.properties.Effort;
import domain.planet.properties.Industry;
import domain.planet.properties.Population;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EffortTest {

	@Test
	void test_effort_industry_500_population_500() {
		Effort effort = new Effort(new Industry(500.0), new Population(500.0));
		assertEquals(500.0, effort.value());
	}

	@Test
	void test_effort_industry_250_population_500() {
		Effort effort = new Effort(new Industry(250.0), new Population(500.0));
		assertEquals(312.5, effort.value());
	}

	@Test
	void test_effort_industry_0_population_500() {
		Effort effort = new Effort(new Industry(0.0), new Population(500.0));
		assertEquals(125.0, effort.value());
	}

}
