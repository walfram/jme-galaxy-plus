package galaxy.ships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoBayTest {

	@Test
	void test_capacity() {
		assertEquals(1.05, new CargoBayOf(1.0).capacity());
		assertEquals(6.25, new CargoBayOf(5.0).capacity());
		assertEquals(15.0, new CargoBayOf(10.0).capacity());
		assertEquals(175.0, new CargoBayOf(50.0).capacity());
		assertEquals(600.0, new CargoBayOf(100.0).capacity());
	}

	@Test
	void should_return_capacity_and_size_tech_level_1() {
		CargoBay cargoBay = new CargoBayOf(100.0);
		assertEquals(100.0, cargoBay.size());
		assertEquals(600.0, cargoBay.capacity());
	}

	@Test
	void should_return_capacity_and_size_tech_level_2() {
		CargoBay cargoBay = new CargoBayOf(100.0, new TechLevel(2.0));
		assertEquals(100.0, cargoBay.size());
		assertEquals(1200.0, cargoBay.capacity());
	}

}
