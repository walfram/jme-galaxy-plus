package galaxy.planet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialsTest {

	@Test
	void test_cannot_withdraw_too_much() {
		Materials materials = new Materials(100.0);
		assertThrows(IllegalArgumentException.class, () -> materials.withdraw(200.0));
	}

	@Test
	void test_withdraw_materials() {
		Materials materials = new Materials(100.0);

		Materials chunk = materials.withdraw(50.0);

		assertEquals(50.0, chunk.value());
		assertEquals(50.0, materials.value());
	}

	@Test
	void test_add_materials() {
		Materials materials = new Materials();
		assertEquals(0.0, materials.value());

		materials.add(new Materials(100.0));
		assertEquals(100.0, materials.value());
	}

}
