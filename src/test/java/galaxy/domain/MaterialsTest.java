package galaxy.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialsTest {

	@Test
	void updating_materials_never_produces_negative_value() {
		Materials materials = new Materials(100.0);
		materials.update(-200.0);
		assertEquals(0.0, materials.value());
	}

}
