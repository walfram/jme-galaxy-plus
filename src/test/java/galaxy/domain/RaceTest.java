package galaxy.domain;

import galaxy.domain.planet.ClassicDaughterWorld;
import galaxy.domain.planet.ClassicHomeWorld;
import galaxy.domain.planet.Coordinates;
import galaxy.domain.technology.CargoTechnology;
import galaxy.domain.technology.EnginesTechnology;
import galaxy.domain.technology.ShieldsTechnology;
import galaxy.domain.technology.WeaponsTechnology;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

	@Test
	void test_race_init() {
		Race race = Fixtures.race();

		assertEquals(3, race.planets().size());

		assertNotNull(race.technologies());
		assertEquals(new EnginesTechnology(), race.technologies().engines());
		assertEquals(new WeaponsTechnology(), race.technologies().weapons());
		assertEquals(new ShieldsTechnology(), race.technologies().shields());
		assertEquals(new CargoTechnology(), race.technologies().cargo());

		assertEquals(0, race.shipTemplates().size());
		assertEquals(0, race.ships().size());

		assertEquals(0, race.routes().size());
	}

}
