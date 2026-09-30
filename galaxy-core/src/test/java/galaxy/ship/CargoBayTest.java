package galaxy.ship;

import galaxy.Race;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoBayTest {

	private final Race race = new Race("test");

	private final ShipType type = new ShipType("container", new CargoBay(100.0));

	@Test
	void test_capacity() {
		assertEquals(1.05, new CargoBay(1.0).capacity());
		assertEquals(6.25, new CargoBay(5.0).capacity());
		assertEquals(15.0, new CargoBay(10.0).capacity());
		assertEquals(175.0, new CargoBay(50.0).capacity());
		assertEquals(600.0, new CargoBay(100.0).capacity());
	}

	@Test
	void should_return_capacity_and_size_tech_level_1() {
		CargoBay cargoBay = new CargoBay(100.0);
		assertEquals(100.0, cargoBay.size());
		assertEquals(600.0, cargoBay.capacity());
	}

	@Test
	void should_return_capacity_and_size_tech_level_2() {
		CargoBay cargoBay = new CargoBay(100.0);
		assertEquals(100.0, cargoBay.size());
		assertEquals(1200.0, cargoBay.capacity(new TechLevel(2.0)));
	}

}
