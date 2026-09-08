package galaxy;

import galaxy.ship.CargoHold;
import galaxy.ship.Engines;
import galaxy.ship.Shields;
import galaxy.ship.Weapons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RaceTest {

	@Test
	void check_race_properties() {
		Race race = new Race("foo");

		assertEquals("foo", race.name());
		assertEquals(new TechLevels(1.0, 1.0, 1.0, 1.0), race.techLevels());
		assertEquals(0, race.shipTypes().all().size());
	}

	@Test
	void check_ship_type_lookup() {
		ShipType scout = new ShipType(
				"scout",
				new Engines(2.0),
				new Weapons(0, 0.0),
				new Shields(1.0),
				new CargoHold(0.0)
		);

		Race race = new Race("terran")
				.withShipType(scout);

		assertEquals(scout, race.shipTypes().byName("scout"));
	}

	@Test
	void check_unknown_ship_type_throws() {
		Race race = new Race("terran");

		assertThrows(IllegalArgumentException.class, () -> race.shipTypes().byName("unknown"));
	}

}
