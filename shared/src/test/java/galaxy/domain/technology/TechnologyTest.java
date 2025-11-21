package galaxy.domain.technology;

import galaxy.domain.planet.Effort;
import galaxy.domain.planet.Industry;
import galaxy.domain.planet.Population;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TechnologyTest {

	@Test
	void test_upgrading_tech_level() {
		Technology engines = new EnginesTechnology();
		Effort effort500 = new Effort(new Industry(500.0), new Population(500.0));
		engines.upgrade(effort500);
		assertEquals(1.1, engines.value());

		Technology weapons = new WeaponsTechnology();
		Effort effort5000 = new Effort(new Industry(5000.0), new Population(5000.0));
		weapons.upgrade(effort5000);
		assertEquals(2, weapons.value());
	}

	@Test
	void test_default_tech_levels() {
		Technology engines = new EnginesTechnology();
		assertEquals(1, engines.value());

		Technology weapons = new WeaponsTechnology();
		assertEquals(1, weapons.value());

		Technology shields = new ShieldsTechnology();
		assertEquals(1, shields.value());

		Technology cargo = new CargoTechnology();
		assertEquals(1, cargo.value());
	}

}
