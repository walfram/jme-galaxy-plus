package galaxy.derived;

import galaxy.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ColonistsTest {

	@Test
	void test_no_colonists() {
		Planet planet = mock(Planet.class);

		when(planet.size()).thenReturn(1000.0);
		when(planet.population()).thenReturn(1000.0);

		Colonists colonists = new Colonists(planet);

		assertEquals(0.0, colonists.value());
	}

	@Test
	void test_colonists() {
		Planet planet = mock(Planet.class);

		when(planet.size()).thenReturn(1000.0);
		when(planet.population()).thenReturn(1080.0);

		Colonists colonists = new Colonists(planet);

		assertEquals(10.0, colonists.value());
	}

}
