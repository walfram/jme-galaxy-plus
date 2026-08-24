package galaxy.core;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipGroupTest {

	@Test
	void when_group_is_created_then_race_tech_levels_are_bound_to_it_with_ship_type() {
		Race race = new Race("test");
		race.changeTechLevel(Technology.ENGINES, 2.0);
		race.changeTechLevel(Technology.WEAPONS, 3.0);
		race.changeTechLevel(Technology.SHIELDS, 4.0);
		race.changeTechLevel(Technology.CARGO, 5.0);

		Planet planet = mock(Planet.class);

		ShipType type = new ShipType(80.0, 2, 2.0, 30.0, 100.0, "MegaFreighter");
		ShipGroup group = new ShipGroup(UUID.randomUUID().toString(), race, type, 20, planet);

		assertEquals(new TechLevels(2.0, 3.0, 4.0, 5.0), group.techLevels());
		assertEquals(type, group.shipType());
		assertEquals(20, group.size());

		race.changeTechLevel(Technology.ENGINES, 3.0);
		assertEquals(new TechLevels(2.0, 3.0, 4.0, 5.0), group.techLevels());
	}

	@Test
	void ship_group_has_owner_and_source_planet() {
		Race race = mock(Race.class);
		when(race.techLevels()).thenReturn(new TechLevels(1.0, 1.0, 1.0, 1.0));

		ShipType type = mock(ShipType.class);

		int size = 32;
		Planet planet = mock(Planet.class);

		ShipGroup shipGroup = new ShipGroup(UUID.randomUUID().toString(), race, type, size, planet);

		assertEquals(race, shipGroup.owner());
		assertEquals(planet, shipGroup.currentPlanet());
	}

}
