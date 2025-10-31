package galaxy.domain.technology;

import galaxy.domain.Effort;
import galaxy.domain.Industry;
import galaxy.domain.Population;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TechnologyTest {

	@Test
	void test_upgrading_tech_level() {
		Technology drive = new DriveTechnology();
		Effort effort500 = new Effort(new Industry(500f), new Population(500f));
		drive.upgrade(effort500);
		assertEquals(1.1f, drive.value());

		Technology weapons = new WeaponsTechnology();
		Effort effort5000 = new Effort(new Industry(5000f), new Population(5000f));
		weapons.upgrade(effort5000);
		assertEquals(2f, weapons.value());
	}

	@Test
	void test_default_tech_levels() {
		Technology drive = new DriveTechnology();
		assertEquals(1f, drive.value());

		Technology weapons = new WeaponsTechnology();
		assertEquals(1f, weapons.value());

		Technology shields = new ShieldsTechnology();
		assertEquals(1f, shields.value());

		Technology cargo = new CargoTechnology();
		assertEquals(1f, cargo.value());
	}

}
