package galaxy.domain.planet;

import galaxy.domain.planet.properties.Colonists;
import galaxy.domain.planet.properties.Population;
import galaxy.domain.planet.properties.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColonistsTest {

	@Test
	void test_colonists_for_population_1100_size_1000() {
		Colonists colonists = new Colonists(new Population(1100.0), new Size(1000.0));
		assertEquals(12.5, colonists.value());
	}

	@Test
	void test_colonists_for_population_1000_size_1000() {
		Colonists colonists = new Colonists(new Population(1000.0), new Size(1000.0));
		assertEquals(0.0, colonists.value());
	}

	@Test
	void test_colonists_for_population_1200_size_1000() {
		Colonists colonists = new Colonists(new Population(1200.0), new Size(1000.0));
		assertEquals(25.0, colonists.value());
	}

}
