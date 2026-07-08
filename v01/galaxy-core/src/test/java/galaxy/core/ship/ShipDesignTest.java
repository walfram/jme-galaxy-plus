package galaxy.core.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipDesignTest {

	@Test
	void test_turrel_9x11_weight() {
		ShipDesign design = new ShipDesign(99, 9, 11, 43, 1);
		assertEquals(198.0, design.weight());
	}

	@Test
	void test_gunship_weight() {
		ShipDesign design = new ShipDesign(4, 2, 2, 4, 0);
		assertEquals(11.0, design.weight());
	}

	@Test
	void test_cruiser_weight() {
		ShipDesign design = new ShipDesign(15, 1, 15, 15, 0);
		assertEquals(45.0, design.weight());
	}

	@Test
	void test_freighter_weight() {
		ShipDesign design = new ShipDesign(8, 0, 0, 2, 10);
		assertEquals(20.0, design.weight());
	}

	@Test
	void test_drone_weight() {
		ShipDesign design = new ShipDesign(1, 0, 0, 0, 0);
		assertEquals(1.0, design.weight());
	}

}
