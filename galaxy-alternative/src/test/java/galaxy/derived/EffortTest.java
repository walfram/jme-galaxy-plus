package galaxy.derived;

import galaxy.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EffortTest {

	@Test
	void test_effort_no_industry_no_population() {
		Planet planet = mock(Planet.class);

		when(planet.industry()).thenReturn(0.0);
		when(planet.population()).thenReturn(0.0);

		Effort effort = new Effort(planet);

		assertEquals(0.0, effort.value());
	}

	@Test
	void test_effort_population_only() {
		Planet planet = mock(Planet.class);

		when(planet.industry()).thenReturn(0.0);
		when(planet.population()).thenReturn(1000.0);

		Effort effort = new Effort(planet);

		assertEquals(250.0, effort.value());
	}

	@Test
	void test_effort_industry_only() {
		Planet planet = mock(Planet.class);

		when(planet.industry()).thenReturn(1000.0);
		when(planet.population()).thenReturn(0.0);

		Effort effort = new Effort(planet);

		assertEquals(750.0, effort.value());
	}

	@Test
	void test_effort_500() {
		Planet planet = mock(Planet.class);

		when(planet.industry()).thenReturn(500.0);
		when(planet.population()).thenReturn(500.0);

		Effort effort = new Effort(planet);

		assertEquals(500.0, effort.value());
	}

	@Test
	void test_effort_1000() {
		Planet planet = mock(Planet.class);

		when(planet.industry()).thenReturn(1000.0);
		when(planet.population()).thenReturn(1000.0);

		Effort effort = new Effort(planet);

		assertEquals(1000.0, effort.value());
	}

}
