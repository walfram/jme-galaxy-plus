package galaxy.ship;

import fixtures.ShipTypeFixtures;
import galaxy.Race;
import galaxy.TechLevels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipGroupTest {

	@Test
	void test_max_travel_distance_at_engine_tech_level_2() {
		Race race = mock(Race.class);
		when(race.techLevels()).thenReturn(new TechLevels(2, 1, 1, 1));

		ShipType drone = ShipTypeFixtures.drone();

		ShipGroup group = new ShipGroup(race, drone, race.techLevels(), 1);
		assertEquals(80.0, group.maxFlightDistance());
	}

	@Test
	void test_max_travel_distance_at_engine_tech_level_1() {
		Race race = new Race("test");

		ShipType drone = ShipTypeFixtures.drone();

		ShipGroup group = new ShipGroup(race, drone, race.techLevels(), 1);
		assertEquals(40.0, group.maxFlightDistance());
	}

}
