package galaxy.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipGroupTest {

	@Test
	void when_group_is_created_then_race_tech_levels_are_bound_to_it_with_ship_type() {
		Race race = new Race("test");

		race.changeTechLevel(Technology.ENGINES, 2.0);
		race.changeTechLevel(Technology.WEAPONS, 3.0);
		race.changeTechLevel(Technology.SHIELDS, 4.0);
		race.changeTechLevel(Technology.CARGO, 5.0);

		ShipType type = new ShipType(80.0, 2, 2.0, 30.0, 100.0, "MegaFreighter");
		ShipGroup group = new ShipGroup(race, type, 20);

		assertEquals(new TechLevels(2.0, 3.0, 4.0, 5.0), group.techLevels());
		assertEquals(type, group.shipType());
		assertEquals(20, group.size());
	}

}
